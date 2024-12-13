<%-- 
    Document   : taklimatPelaksanaWaran
    Created on : May 27, 2011, 4:19:10 PM
    Author     : faidzal
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    function save(event, f){
        
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.get(url,q,
        function(data){
            $('#page_div').html(data);

        },'html');

    }
</script>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form name="form1" beanclass="etanah.view.strata.PerakuanKeputusanWaranActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>${actionBean.legend}</legend>
            <s:hidden name="idStor"/>
            <p>

                <label> Taklimat :</label><s:textarea class="normal_text" name="taklimatWaran" readonly="${actionBean.readOnly}"  rows="6" cols="70"/>
            </p>
            <p>
                <c:if test="${!actionBean.readOnly}">
                    <label>&nbsp;</label> <s:button name="saveTaklimat" value="Simpan" class="btn" onclick="save(this.name, this.form);" />
                </c:if></p>

        </fieldset>
    </div>
</s:form>

