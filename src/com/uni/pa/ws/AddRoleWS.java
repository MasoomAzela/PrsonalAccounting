package com.uni.pa.ws;

import com.uni.pa.assets.PermissionParam;
import com.uni.pa.dto.AddRoleDto;
import com.uni.pa.logic.AddRoleBL;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;

import static com.uni.pa.constants.RequestAttributeConstants.errorFlag;

@WebServlet(name = "addRoleService", urlPatterns = "/addrole")
public class AddRoleWS extends HttpServlet {
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        try {

            AddRoleDto addRoleDto = new AddRoleDto();
            String description = httpServletRequest.getParameter("description");
            String name = httpServletRequest.getParameter("name");

            addRoleDto.setName(name);
            addRoleDto.setDescription(description);

            Map<String, String[]> m = httpServletRequest.getParameterMap();
            String[] permissions = m.get("permission");

            List<PermissionParam> permissionParams = new ArrayList<>();
            for (int i = 0; i < permissions.length ; i++) {
                permissionParams.add(PermissionParam.valueOf(permissions[i]));
            }

            addRoleDto.setPermissions(permissionParams);

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<AddRoleDto>> constraintViolations = validator.validate(addRoleDto);

            if (constraintViolations.size() != 0) {
                Iterator<ConstraintViolation<AddRoleDto>> iterator = constraintViolations.iterator();
                ConstraintViolation constraintViolation = iterator.next();
                httpServletRequest.setAttribute(errorFlag, constraintViolation.getMessage());
                httpServletRequest.getRequestDispatcher("/addRole.jsp").forward(httpServletRequest, httpServletResponse);
            }

            AddRoleBL addRoleBL = new AddRoleBL();
            addRoleBL.roleInsert(name, description, permissionParams);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}