### Which is the SuT (subject under test)? Which is the service to mock?

A classe AddressResolver tem um método findAddressForLocation() que chama um método de outra classe doHttpGet(), logo a classe AddressResolver tem dependencias então é o SuT.
ISimpleHttpClient é a classe em que vamos fazer mock , pois é esta que contem o método que é chamado na classe AddressResolver.
