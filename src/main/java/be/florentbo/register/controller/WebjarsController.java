package be.florentbo.register.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.webjars.WebJarAssetLocator;

@Controller
public class WebjarsController {

    @ResponseBody
    @RequestMapping(value="/webjarslocator/{webjar}/**", method= RequestMethod.GET)
    public ResponseEntity<Resource> locateWebjarAsset(@PathVariable String webjar, WebRequest request) {
        try {
            String mvcPrefix = "/webjarslocator/" + webjar + "/";
            String mvcPath = (String) request.getAttribute(
                    HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);

            WebJarAssetLocator locator = new WebJarAssetLocator();
            String fullPath = locator.getFullPath(webjar,mvcPath.substring(mvcPrefix.length()));
            ClassPathResource res = new ClassPathResource(fullPath);
            long lastModified = res.lastModified();
            if ((lastModified > 0) && request.checkNotModified(lastModified)) {
                return null;
            }
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
