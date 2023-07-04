Decoder – ResponseEntityDecoder, which wraps SpringDecoder, used to decode the Response
Encoder – SpringEncoder is used to encode the RequestBody.
Logger – Slf4jLogger is the default logger used by Feign.
Contract – SpringMvcContract, which provides annotation processing
Feign-Builder – HystrixFeign.Builder is used to construct the components.
Client – LoadBalancerFeignClient or default Feign client