<%--
    Document   : carian_hakmilik
    Created on : 15-Oct-2009, 10:22:28
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
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        tablecloth();
        $(".popup").click( function() {

            window.open("pihak?pihakDetail&idpihak="+$("#hakmilik").val()+"&idHakmilik="+${actionBean.idHakmilik}, "eTanah",
            "status=0,toolbar=0,location=1,menubar=0,width=900,height=600");
        });
         $("#popuptambahpihak").click( function() {
            window.open("pihakBerkepentingan?idHakmilik="+${actionBean.idHakmilik}, "eTanah",
            "status=0,toolbar=0,location=1,menubar=0,width=900,height=600");
        });
        <%----LAMA PUNYA----%>
        <%--$("#add").click( function(){

            frm = this.form;
            //TODO :
            var len = $(".nokp1").length;

            for(var i=1; i<=len; i++){
                if($('#chkbox'+i).is(':checked')){
                    var id = $(".idpihak"+i).text();
                    var nama = $(".nama"+i).text();
                    var nokp = $(".nokp"+i).text();
                    var tablename = "linepihak";
                    alert("nama :"+nama);
                    alert("nokp"+nokp);
                    self.opener.addRowsPihak(nama , nokp,tablename);

                }
            }
            self.close();
        });--%>
        $("#add").click( function(){
            var f = this.form;
            //TODO :
            //var len = $(".checkbox").length;
            //for looping purpose
            var len = ${fn:length(actionBean.list)};

            for(var i=1; i<=len; i++){
                if($('#chkbox'+i).is(':checked')){
                    var syer1 = $('#syerA'+i).val();
                    var syer2 = $('#syerB'+i).val();
                    if(syer1 == '' || syer2 == ''){
                        alert('Sila Masukkan Syer.');
                        return;
                    }
                    var nama = $('.nama'+i).text();
                    var kp = $('.nokp'+i).text();
                    var url = '${pageContext.request.contextPath}/pihakBerkepentingan?simpanPihakKepentingan&idPihak='+$('#chkbox'+i).val()+'&syerA='+syer1+'&syerB='+syer2+'&idHakmilik='+$('#idHakmilik').val();
                   <%-- alert(url);--%>
                    $.post(url,
                    function(data){
                       <%-- alert(data);--%>
                        $('#page_div',parent.opener.document).html(data);

                        <%-- $(parent.document).find("#page_div").html(data);--%>
                    });
                    //self.opener.addRowMohonPihak(nama, kp, syer);
                }
            }
            <%--setTimeout(function(){
                self.close();
            }, 1500);--%>
        });
    });
</script>
<div class="subtitle">
    <form:form beanclass="etanah.view.daftar.MaklumatPihakActionBean">
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <fieldset class="aras1">
            <legend>
                Carian Pihak
            </legend>

            <p>
                <label for="Nama Pihak">Nama Pihak:</label>
                <s:text name="pihak.nama" id="pihak"/>
            </p>
             <p>
                <label for="Nama Pihak">No Kp:</label>
                <s:text name="pihak.noPengenalan"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:submit name="searchPihak" value="Cari" class="btn"/> &nbsp;  <c:if test="${fn:length(actionBean.list) < 1}"><s:button name="popuptambahpihak" id="popuptambahpihak" value="Tambah Baru" class="btn"/></c:if>
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
                <display:table class="tablecloth" name="${actionBean.list}" pagesize="4" cellpadding="0" cellspacing="0" requestURI="pihak" id="line">
                    <display:column> <s:checkbox name="chkbox" id="chkbox${line_rowNum}" value="${line.idPihak}" class="checkbox"/></display:column>
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="nama" title="Nama" class="nama${line_rowNum}" />
                    <display:column property="noPengenalan" title="No KP" class="nokp${line_rowNum}"/>
                    <display:column title="Syer"><s:text name="syer1" id="syerA${line_rowNum}" size="5"/>/
                        <s:text name="syer2" id="syerB${line_rowNum}" size="5"/></display:column>
                </display:table>
            </div>
            <c:if test="${fn:length(actionBean.list) > 0}">
                <p>
                    <label>&nbsp;</label>
                    <s:button name="add" id="add" value="Tambah" class="btn"/>
                </p>
            </c:if>
        </fieldset>
    </form:form>
</div>
