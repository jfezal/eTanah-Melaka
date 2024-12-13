<%-- 
    Document   : terima_notis
    Created on : Jul 12, 2011, 1:07:06 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<script type="text/javascript">
    
    function validation() {
//        var count = $("#count").val();
//        alert(count);
//        for(var i=1;i<=count;i++){
            var tarikhTerimaBicara = $("#tarikhTerimaBicara").val();
              if(tarikhTerimaBicara == ""){
                alert('Sila pilih " Tarikh Terima Perbicaraan " terlebih dahulu.');
                $("#tarikhTerimaBicara").focus();
                return false;
            }
  
//        }
        return true;
    }
    
</script>
<s:form beanclass="etanah.view.stripes.pelupusan.BorangNAdatActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Terima Notis Bicara</legend><br/>
            <div class="content" align="center">
                <display:table class="tablecloth" cellpadding="0" cellspacing="0" name="${actionBean.pemohon}" id="line" 
                               requestURI="${pageContext.request.contextPath}/pelupusan/borangN_adat">
                    <display:column title="Bil" sortable="true">${line_rowNum}
                    </display:column>
                    <display:column property="pihak.nama" title="Nama"/>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                        <display:column title="Tarikh Terima Notis Bicara">
                        <s:format value="${actionBean.tarikhTerimaBicara1}" formatPattern="dd/MM/yyyy"/>
                    </display:column>
       
                </display:table>
            </div>
            <p align="center">
               <s:button name="simpanTerimaBicara" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div');"/>
            </p>

        </fieldset>
    </div>
</s:form>

