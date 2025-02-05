# TaskAPI

TaskAPIは、bpmワークフロー連携のためのAPIです。このAPIは、Apache Kafkaを利用してメッセージ駆動型の処理を行います。

## 目次
[[_TOC_]]

## 機能

- サンプルとして、タスクの作成、取得、削除
- Kafkaを使用したメッセージのリスニング
- Oracle Databaseとの連携

## 技術スタック

- Java 8
- Spring Boot
- Spring Kafka
- MyBatis
- Oracle Database

## セットアップ手順

1. リポジトリをクローンします。
   ```bash
   git clone XXX.git
   cd XXX
   ```

2. 必要な依存関係をダウンロードします。
   ```bash
   ./gradlew build
   ```

3. Oracle Databaseの設定を `application.yml` に記入します。
   ```yaml
   spring:
     datasource:
       url: jdbc:oracle:thin:@localhost:1521/XEPDB1
       username: oracle_user
       password: pass
   ```

4. サンプルとなるタスクテーブルを作成します。
   ```sql
   CREATE TABLE M_TASK (
       task_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
       title VARCHAR2(255) NOT NULL,
       description VARCHAR2(1000),
       completed NUMBER DEFAULT 0
   );
   ```

5. アプリケーションを起動します。
   ```bash
   ./gradlew bootRun
   ```

## Kafkaの起動およびメッセージ送信手順

### 1. Kafkaの起動

#### 1.1 Zookeeperの起動

1. コマンドプロンプトを開きます。
2. Kafkaの`bin/windows`ディレクトリに移動します。
   ```bash
   cd C:\kafka_2.13-3.7.1\bin\windows
   ```
3. Zookeeperを起動します。
   ```bash
   zookeeper-server-start.bat ..\..\config\zookeeper.properties
   ```

#### 1.2 Kafkaの起動

1. 別のコマンドプロンプトを開きます。
2. 同じくKafkaの`bin/windows`ディレクトリに移動します。
   ```bash
   cd C:\kafka_2.13-3.7.1\bin\windows
   ```
3. Kafkaを起動します。
   ```bash
   kafka-server-start.bat ..\..\config\server.properties
   ```

### 2. トピックの作成

1. Kafkaの`bin/windows`ディレクトリに移動します（すでに移動している場合はスキップ）。
   ```bash
   cd C:\kafka_2.13-3.7.1\bin\windows
   ```
2. トピックを作成します。（すでに作成している場合はスキップ）以下のコマンドでは、トピック名を`task-events`とし、パーティション数を`1`、レプリケーションファクターを`1`に設定します。
   ```bash
   kafka-topics.bat --create --topic task-events --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
   ```
3. トピックが作成されたことを確認するために、トピックの一覧を表示します。
   ```bash
   kafka-topics.bat --list --bootstrap-server localhost:9092
   ```

### 3. メッセージの送信

#### 3.1 コンソールプロデューサーを使用する

1. Kafkaの`bin/windows`ディレクトリに移動します（すでに移動している場合はスキップ）。
   ```bash
   cd C:\kafka_2.13-3.7.1\bin\windows
   ```
2. コンソールプロデューサーを起動します。
   ```bash
   kafka-console-producer.bat --topic task-events --bootstrap-server localhost:9092
   ```
3. 以下のようなJSON形式のメッセージを入力します。
   ```json
   {"apiEndpoint":"/tasks","httpMethod":"POST","params":{"taskId":5,"title":"Sample Task","description":"This is a sample task.","completed":0}}
   ```

### 4. メッセージの確認

1. 別のコマンドプロンプトを開き、Kafkaのコンシューマーを起動します。
   ```bash
   kafka-console-consumer.bat --topic task-events --from-beginning --bootstrap-server localhost:9092
   ```
2. 送信したメッセージが表示されることを確認します。
3. 作成したM_TASKテーブルにレコードが登録されていることを確認します。

## 注意事項
- ZookeeperとKafkaはそれぞれ別のコマンドプロンプトで実行してください。
- メッセージが受信できない場合は、メッセージフォーマットやトピック名を確認してください。

## APIエンドポイント

- `GET /tasks` - すべてのタスクを取得
- `POST /tasks` - 新しいタスクを作成
- `DELETE /tasks/{taskId}` - 指定されたIDのタスクを削除
