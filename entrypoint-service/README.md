# Spring boot Open Telemetry Demo

This directory contains a Docker Compose environment that can be used to test
Grafana Alloy.

> **NOTE**: This environment is not intended for production use, and is
> maintained on a best-effort basis.

By default, only Grafana and databases are exposed:

* Grafana, for visualizing telemetry [http://localhost:3000](http://localhost:3000)
* Grafana Mimir, for storing metrics [http://localhost:9009](http://localhost:9009)
* Grafana Loki, for storing logs (`localhost:3100`)
* Grafana Tempo, for storing traces (`localhost:3200`)
* Grafana Pyroscope, for storing profiles (`localhost:4040`)
* Grafana Alloy, for receiving and exporting OTEL metrics, traces and logs [http://localhost:12345](http://localhost:12345)

Grafana is automatically provisioned with the appropriate datasources and
dashboards for monitoring Grafana Alloy.


To create Docker image of application, run the following command.
```text
docker image build . -t spring-boot-observability-demo:0.0.1 -f Dockerfile
```

To start the environment, run:

```bash
docker compose up --build -d
```

To stop the environment, run:

```bash
docker compose down
```

## Visualizing

To visualize Alloy data in Grafana, open <http://localhost:3000> in a web browser.

> **NOTE**: It can take up to a minute for Alloy metrics and profiles to start appearing.


