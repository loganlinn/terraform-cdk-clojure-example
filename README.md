# terraform-cdk-clojure-example

This is a proof-of-concept for using Terraform CDK with Clojure.

## Notes

Use the `cdktf` CLI to download & generate Java sources based on [`cdktf.json`](cdktf.json):

```shell
npx -p cdktf-cli cdktf get
```

Compile the Java sources:

```shell
clj -T:build compile
```

Synthesize the example stack:

```shell
clj -X cdktf-azure-example/synth
```

Refer to [`deps.edn`](deps.edn).
