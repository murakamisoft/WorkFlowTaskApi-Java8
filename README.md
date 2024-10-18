# TaskAPI

TaskAPIは、bpmワークフロー連携のためのAPIです。このAPIは、Apache Kafkaを利用してメッセージ駆動型の処理を行います。

## 目次
[[_TOC_]]

## 機能

- タスクの作成、取得、削除
- Kafkaを使用したメッセージのリスニング
- Oracle Databaseとの連携

## 技術スタック

- Java 8
- Spring Boot
- Spring Kafka
- MyBatis
- Oracle Database
- Lombok

## セットアップ手順

1. リポジトリをクローンします。
   ```bash
   git clone https://github.com/bmpworkflow/WorkFlowTaskApi-Java8.git
   cd WorkFlowTaskApi
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

4. アプリケーションを起動します。
   ```bash
   ./gradlew bootRun
   ```

## APIエンドポイント

- `GET /tasks` - すべてのタスクを取得
- `POST /tasks` - 新しいタスクを作成
- `DELETE /tasks/{taskId}` - 指定されたIDのタスクを削除

