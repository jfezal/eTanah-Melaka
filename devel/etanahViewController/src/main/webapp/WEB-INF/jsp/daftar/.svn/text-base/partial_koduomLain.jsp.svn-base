<%-- 
    Document   : partial_koduom
    Created on : Jul 1, 2010, 10:51:46 AM
    Author     : khairil
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready( function(){
        $('#kodUOMLain').val($('#kodUnitLuasLain').val());
                
            });
</script>
<s:form partial="true" beanclass="etanah.view.stripes.KemasukanPerincianHakmilikActionBean">
    <s:select  style="text-transform:uppercase" id="kodUOMLain" name="kodUnitLuasLain">
        <s:option value="">Sila Pilih</s:option>
        <s:options-collection collection="${actionBean.senaraiKodUOM}" label="nama" value="kod"/>
    </s:select>
</s:form>