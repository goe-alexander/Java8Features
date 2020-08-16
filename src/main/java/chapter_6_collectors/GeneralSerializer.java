package chapter_6_collectors;

public class GeneralSerializer {
 /*   @Autowired private ObjectMapper objectMapper;

    public String toJson(Object rootDTO) {
        if (rootDTO == null) {
            return "null";
        }
        try {
            return objectMapper.writeValueAsString(rootDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T fromJson(String jsonObject, Class<T> rootDTOClass) throws ServiceException {
        return fromJsonGivenType(jsonObject, rootDTOClass);
    }

    public <T> T fromJson(String jsonObject, TypeReference<T> typeReference) throws ServiceException {
        return fromJsonGivenType(jsonObject, typeReference);
    }

    public <T> T fromJson(String jsonObject, JavaType javaType) throws IOException {
        return fromJsonGivenType(jsonObject, javaType);
    }

    private <T> T fromJsonGivenType(String jsonObject, Class<T> classType) throws ServiceException {
        if (StringUtils.isBlank(jsonObject)) return null;
        try {
            isValidJSON(jsonObject);
            return objectMapper.readValue(jsonObject, classType);
        } catch (InvalidFormatException ee) {
            throw new ServiceException(INVALID_INPUT, ee);
        } catch (JsonMappingException jme) {
            throw new ServiceException(jme);
        } catch (NumberFormatException ne) {
            throw new ServiceException(ne);
        } catch (IOException e) {
            throw new ServiceException(INVALID_INPUT, e);
        }
    }

    private <T> T fromJsonGivenType(String jsonObject, JavaType javaType) throws IOException {
        if (StringUtils.isBlank(jsonObject)) return null;
        try {
            isValidJSON(jsonObject);
            return objectMapper.readValue(jsonObject, javaType);
        } catch (InvalidFormatException e) {
            throw new InvalidJsonValueException(e.getMessage(), e);
        } catch (JsonMappingException | NumberFormatException ee) {
            throw new IOException(ee);
        }
    }

    private <T> T fromJsonGivenType(String jsonObject, TypeReference<T> typeRef)
            throws ServiceException {
        if (StringUtils.isBlank(jsonObject)) return null;
        try {
            isValidJSON(jsonObject);
            return objectMapper.readValue(jsonObject, typeRef);
        } catch (InvalidFormatException ee) {
            throw new ServiceException(INVALID_INPUT, ee);
        } catch (JsonMappingException jme) {
            throw new ServiceException(jme);
        } catch (NumberFormatException ne) {
            throw new ServiceException(ne);
        } catch (IOException e) {
            throw new ServiceException(INVALID_INPUT, e);
        }
    }

    private void isValidJSON(final String json) throws IOException {
        JsonParser parser = null;
        try {
            parser = objectMapper.getFactory().createParser(json);
            while (parser.nextToken() != null) {}
        } finally {
            if (parser != null) parser.close();
        }
    }*/
}
