import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glodon.digiarch.paas.api.definition.common.entity.req.DefinitionCreateReq;
import com.glodon.digiarch.paas.api.definition.common.entity.req.ParamCreateReq;
import com.glodon.digiarch.paas.api.definition.common.enums.HttpMethod;
import com.glodon.digiarch.paas.api.definition.common.enums.ParamLocation;
import com.glodon.digiarch.paas.api.definition.common.enums.ValueType;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.parser.ObjectMapperFactory;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import io.swagger.v3.parser.util.OpenAPIDeserializer;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyx-l
 * @version 1.0
 * @date 2020/4/1 7:04 下午
 */
public class SwaggerTest {

    public static void main(String[] args) throws Exception{

        /*ObjectMapper mapper =  ObjectMapperFactory.createJson();

        String s = streamToString(new FileInputStream(new File("/Users/xiang/Downloads/swagger.json")));

        JsonNode rootNode = mapper.readTree(s);

        AecoreAPIDeserializer aecoreAPIDeserializer = new AecoreAPIDeserializer();

        SwaggerParseResult swaggerParseResult = aecoreAPIDeserializer.deserialize(rootNode);*/


        OpenAPI openAPI = new OpenAPIV3Parser().read("http://aecore-collector-test.glodon.com/static/collector/swagger.json");

        List<DefinitionCreateReq> definitionCreateReqs = new ArrayList<>();

        openAPI.getPaths().entrySet().stream().forEach(stringPathItemEntry -> {
            String path = stringPathItemEntry.getKey();
            PathItem pathItem = stringPathItemEntry.getValue();
            if(pathItem.getPost() != null) {
                test(path, HttpMethod.POST, pathItem.getPost(), definitionCreateReqs);
            }
            if(pathItem.getGet() != null) {
                test(path, HttpMethod.GET, pathItem.getGet(), definitionCreateReqs);
            }
            if(pathItem.getPut() != null) {
                test(path, HttpMethod.PUT, pathItem.getPut(), definitionCreateReqs);
            }
            if(pathItem.getPatch() != null) {
                test(path, HttpMethod.PATCH, pathItem.getPatch(), definitionCreateReqs);
            }
            if(pathItem.getDelete() != null) {
                test(path, HttpMethod.DELETE, pathItem.getDelete(), definitionCreateReqs);
            }
            if(pathItem.getOptions() != null) {
                test(path, HttpMethod.OPTIONS, pathItem.getOptions(), definitionCreateReqs);
            }
        });
        System.out.println("---");

    }

    private static void test(String path, HttpMethod httpMethod, Operation operation, List<DefinitionCreateReq> definitionCreateReqs) {
        DefinitionCreateReq definitionCreateReq = new DefinitionCreateReq();
        definitionCreateReq.setPathOnBackend(path);
        definitionCreateReq.setPathOnGateway(path);
        definitionCreateReq.setMethodOnBackend(httpMethod);
        definitionCreateReq.setMethodOnGateway(httpMethod);
        String description = operation.getDescription();
        definitionCreateReq.setDescription(description);
        // TODO security 是啥
        // TODO name如何选择
        definitionCreateReq.setName(description);
        // 参数非空 设置参数
        if(!CollectionUtils.isEmpty(operation.getParameters())) {
            List<ParamCreateReq> paramCreateReqs = new ArrayList<>();
            operation.getParameters().stream().forEach(parameter -> {
                ParamCreateReq paramCreateReq = new ParamCreateReq();
                String name = parameter.getName();
                paramCreateReq.setNameOnGateway(name);
                paramCreateReq.setNameOnBackend(name);
                paramCreateReq.setDescription(parameter.getDescription());
                // TODO 默认值是啥
                ParamLocation paramLocation = ParamLocation.parseCodeToEnum(parameter.getIn());
                paramCreateReq.setLocationOnBackend(paramLocation);
                paramCreateReq.setLocationOnGateway(paramLocation);
                paramCreateReq.setRequired(parameter.getRequired());
                //paramCreateReq.setValueType(ValueType.parseCodeToEnum(parameter.getSchema().getType()));
                paramCreateReqs.add(paramCreateReq);
                definitionCreateReq.setParamCreateReqs(paramCreateReqs);
            });
        }
        definitionCreateReqs.add(definitionCreateReq);
    }

    private static String streamToString(InputStream in) throws Exception{

        StringBuilder contents = new StringBuilder();

        BufferedReader input = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

        try {
            for (int i = 0; i != -1; i = input.read()) {
                char c = (char) i;
                if (!Character.isISOControl(c)) {
                    contents.append((char) i);
                }
                if (c == '\n') {
                    contents.append('\n');
                }
            }
        } catch (IOException e) {
            throw new Exception(e);
        } finally {
            input.close();
            in.close();
        }

        return contents.toString();
    }
}
