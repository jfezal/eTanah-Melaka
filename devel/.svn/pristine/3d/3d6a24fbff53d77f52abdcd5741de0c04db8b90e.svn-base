<%-- 
    Document   : terimaBayaran
    Created on : Jul 12, 2011, 12:58:01 PM
    Author     : Administrator
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    $(document).ready( function(){

            var one = document.getElementById('dibayar').value;
//            alert(one);
         
            if(one == 'Telah Dibayar'){
//                alert(document.getElementById('mandatori2').value);
                document.getElementById('check').checked = 'true';
            }
            });
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.TanahAdatActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle1" align="center">
        <fieldset class="aras1">
    <legend>Maklumat Suku Pemohon</legend><br><br>
    <s:hidden name="dibayar" id="dibayar"/>
    <display:table class="tablecloth" name="${actionBean.pemohon}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/tanahAdat">
        <display:column title="Nama" property="pihak.nama"/>
        <display:column title="Bayaran">RM10.00</display:column>
        <display:column title="Dibayar"><s:checkbox name="check" id="check"></s:checkbox></display:column>
    </display:table>
        <br/>
            <p>
               <s:button name="simpanBayaran" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
            </p>
</fieldset>
</div>

</s:form>
