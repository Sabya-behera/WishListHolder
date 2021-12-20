package com.example.Like_and_Unlike_feature.Model;

public class Nft {
     private String contractAddress;
     private String fromAddress;
     private String receiverAddress;
     private String privateKey;
     private  int   tokenId;

     public Nft() {
     }

     public Nft(String contractAddress, String fromAddress, String receiverAddress, String privateKey, int tokenId) {
          this.contractAddress = contractAddress;
          this.fromAddress = fromAddress;
          this.receiverAddress = receiverAddress;
          this.privateKey = privateKey;
          this.tokenId = tokenId;
     }

     public String getContractAddress() {
          return contractAddress;
     }

     public void setContractAddress(String contractAddress) {
          this.contractAddress = contractAddress;
     }

     public String getFromAddress() {
          return fromAddress;
     }

     public void setFromAddress(String fromAddress) {
          this.fromAddress = fromAddress;
     }

     public String getReceiverAddress() {
          return receiverAddress;
     }

     public void setReceiverAddress(String receiverAddress) {
          this.receiverAddress = receiverAddress;
     }

     public String getPrivateKey() {
          return privateKey;
     }

     public void setPrivateKey(String privateKey) {
          this.privateKey = privateKey;
     }

     public int getTokenId() {
          return tokenId;
     }

     public void setTokenId(int tokenId) {
          this.tokenId = tokenId;
     }
}
