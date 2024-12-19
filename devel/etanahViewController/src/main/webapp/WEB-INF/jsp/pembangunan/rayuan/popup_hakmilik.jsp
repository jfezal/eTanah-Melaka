<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<script type="text/javascript">
         function save(event, f){
        $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });


            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                $.unblockUI();
                self.close();
            },'html');
        }

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>

<s:form  beanclass="etanah.view.stripes.pembangunan.MaklumatPermohonanRayuanActionBean" name="form">

    <s:hidden name="idMohonSblm" id="idMohonSblm" value="${actionBean.permohonanSblm}"/>
    <s:errors/>
    <s:messages/>

    <div id="test" class="subtitle">
        <fieldset class="aras1">
                <legend>Maklumat Hakmilik</legend>
                <p>
                    <label>ID Hakmilik :</label>
                    <s:text name="idHM" id="idHM" size="20" class="normal_text" value=""/>
                    
                    <s:button name="saveHM" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                </p>

        </fieldset>
    </div>


</s:form>
