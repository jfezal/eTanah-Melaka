<%-- 
    Document   : carian_pihak
    Created on : 04-Nov-2009, 12:31:30
    Author     : md.nurfikri
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".subtitle input:text").each( function(el) {
            $(this).blur( function() {
                $(this).val( $(this).val().toUpperCase());
            });
        });

        $(".popup").click( function() {
            window.open("hakmilik?hakmilikDetail&idHakmilik="+$("#hakmilik").val(), "eTanah",
            "status=0,toolbar=0,location=1,menubar=0,width=900,height=600");
        });

        $("#add").click( function(){            
            var f = this.form;
            //TODO :
            //var len = $(".checkbox").length;
            //for looping purpose
            var len = ${fn:length(actionBean.pihakList)};

            for(var i=1; i<=len; i++){
                if($('#chkbox'+i).is(':checked')){
                    <%--var syer1 = $('#syerA'+i).val();
                    var syer2 = $('#syerB'+i).val();
                    if(syer1 == '' || syer2 == ''){
                        alert('Sila Masukkan Syer.');
                        return;
                    }  --%>
                    var nama = $('.nama'+i).text();
                    var kp = $('.noPengenalan'+i).text();                    
                    var url = '${pageContext.request.contextPath}/common/mohon_pihak?save&idPihak='+$('#chkbox'+i).val();
                    $.post(url,
                    function(data){                        
                        $('#page_div',opener.document).html(data);
                    });
                        
                    //self.opener.addRowMohonPihak(nama, kp, syer);
                }
            }
            setTimeout(function(){
                self.close();
            }, 1500);
                        
        });
    });
</script>
<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.common.PihakActionBean">
        <fieldset class="aras1">
            <legend>
                Carian Pihak
            </legend>
            <p>
                <label for="nama pihak">Nama Pihak :</label>
                <s:text name="pihak.nama"/>
            </p>
            <p>
                <label for="noKp">No KP :</label>
                <s:text name="pihak.noPengenalan"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:submit name="searchPihak" value="Cari" class="btn"/>
            </p>
        </fieldset>
    </div>
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Pihak
            </legend>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pihakList}" pagesize="10" cellpadding="0" cellspacing="0"
                               requestURI="${pageContext.request.contextPath}/common/pihak" id="line">
                    <display:column> <s:checkbox name="chkbox" id="chkbox${line_rowNum}" value="${line.idPihak}" class="checkbox"/></display:column>
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="nama" title="Nama Pihak" class="popup nama${line_rowNum}" />
                    <display:column property="noPengenalan" title="No Pengenalan" class="noPengenalan${line_rowNum}"/>
                   <%-- <display:column title="Syer"><s:text name="syer1" id="syerA${line_rowNum}" size="5"/>/
                        <s:text name="syer2" id="syerB${line_rowNum}" size="5"/></display:column>--%>
                </display:table>
            </div>
            <c:if test="${fn:length(actionBean.pihakList) > 0}">
                <p>
                    <label>&nbsp;</label>
                    <s:button name="add" id="add" value="Tambah" class="btn"/>
                </p>
            </c:if>
        </fieldset>
    </s:form>
</div>
