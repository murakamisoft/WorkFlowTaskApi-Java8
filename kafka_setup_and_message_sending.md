# Kafkaの起動およびメッセージ送信手順

## 1. Kafkaの起動

### 1.1 Zookeeperの起動

1. コマンドプロンプトを開きます。
2. Kafkaの`bin/windows`ディレクトリに移動します。
   ```bash
   cd C:\kafka_2.12-3.5.1\bin\windows
   ```
3. Zookeeperを起動します。
   ```bash
   zookeeper-server-start.bat ..\..\config\zookeeper.properties
   ```

### 1.2 Kafkaの起動

1. 別のコマンドプロンプトを開きます。
2. 同じくKafkaの`bin/windows`ディレクトリに移動します。
   ```bash
   cd C:\kafka_2.12-3.5.1\bin\windows
   ```
3. Kafkaを起動します。
   ```bash
   kafka-server-start.bat ..\..\config\server.properties
   ```

## 2. トピックの確認

1. Kafkaの`bin/windows`ディレクトリに移動します（すでに移動している場合はスキップ）。
   ```bash
   cd C:\kafka_2.12-3.5.1\bin\windows
   ```
2. トピックの一覧を確認します。
   ```bash
   kafka-topics.bat --list --bootstrap-server localhost:9092
   ```

## 3. メッセージの送信

### 3.1 コンソールプロデューサーを使用する

1. Kafkaの`bin/windows`ディレクトリに移動します（すでに移動している場合はスキップ）。
   ```bash
   cd C:\kafka_2.12-3.5.1\bin\windows
   ```
2. コンソールプロデューサーを起動します。
   ```bash
   kafka-console-producer.bat --topic task-events --bootstrap-server localhost:9092
   ```
3. 以下のようなJSON形式のメッセージを入力します。
   ```json
   {"apiEndpoint":"/tasks","httpMethod":"POST","params":{"taskId":40,"title":"Sample Task","description":"This is a sample task.","completed":false}}
   ```

## 4. メッセージの確認

1. 別のコマンドプロンプトを開き、Kafkaのコンシューマーを起動します。
   ```bash
   kafka-console-consumer.bat --topic task-events --from-beginning --bootstrap-server localhost:9092
   ```
2. 送信したメッセージが表示されることを確認します。

## 注意事項
- ZookeeperとKafkaはそれぞれ別のコマンドプロンプトで実行してください。
- メッセージが受信できない場合は、メッセージフォーマットやトピック名を確認してください。
