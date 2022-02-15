(ns cdktf-azure-example
  "A Clojure version of terraform-cdk java azure example.
  Reference: https://github.com/hashicorp/terraform-cdk/blob/8ec3840c631b648d68f7103b35a710a249feacd8/examples/java/azure/src/main/java/com/mycompany/app/Main.java"
  (:import
   (com.hashicorp.cdktf App
                        TerraformStack)
   (imports.azurerm AzurermProvider$Builder
                    AzurermProviderFeatures
                    VirtualNetwork$Builder)))

(set! *warn-on-reflection* true)

(defn tf-stack
  ^TerraformStack
  [scope id]
  (let [stack (TerraformStack. scope id)]

    (-> (AzurermProvider$Builder/create stack "AzureRm")
        (.features (-> (AzurermProviderFeatures/builder)
                       ;; ...
                       (.build)))
        (.build))

    (-> (VirtualNetwork$Builder/create stack "TfVnet")
        (.location "uswest")
        (.addressSpace ["10.0.0.0/24"])
        (.name "TerraformVNet")
        (.resourceGroupName "<YOUR_RESOURCE_GROUP_NAME>")
        (.build))

    stack))

(defn synth
  [_]
  (doto (App.)
    (tf-stack "azure")
    (.synth)))
