<%-- 
    Document   : pertanyaan_bil_cukai
    Created on : Mar 22, 2011, 2:30:34 PM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    function carianHakmilik(f,kaedah){
        var negeri = "${actionBean.kodNegeri}";
        var form = $(f).formSerialize();
        var idHakmilik = $("#hakmilik").val();
        var noAkaun = $("#akaun").val();
        if(negeri == "sembilan")
            noAkaun = "";
        if(idHakmilik == "" && noAkaun == ""){
            if(negeri == "melaka")
                alert("Sila masukkan salah satu maklumat berikut.");
            if(negeri == "sembilan")
                alert("Sila masukkan maklumat berikut.");
            return false;
        }
        else{
            return true;
        }
    }

    function paparBil(f,idHakmilik){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var negeri = "${actionBean.kodNegeri}";
        var form = $(f).formSerialize();
        var report = "";
        if(negeri == "melaka")
            report = "HSLBilCukaiMLK_Frame.rdf";
        if(negeri == "sembilan")
            report = "HSLBilCukaiNS_Frame.rdf";
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_hakmilik="+idHakmilik, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
        $.unblockUI();
    }

    function cetakBilCukai(f,idHakmilik,noAkaun){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var form = $(f).formSerialize();
        var report = null;
        var negeri = '${actionBean.kodNegeri}';
        if(negeri == 'melaka'){
            report = 'HSLResitBilCukaiMLK001.rdf';
        }else{
            report = 'HSLResitBilCukaiNS003.rdf';
        }

        <%--f.action = "${pageContext.request.contextPath}/hasil/pertanyaan_bil?bilCetakPenyataRun&"+form+"&idHakmilik="+idHakmilik;
        f.submit();--%>

        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_bil?bilCetakPenyataRun&"+form+"&idHakmilik="+idHakmilik, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");

        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_hakmilik="+idHakmilik+"&report_p_no_akaun="+noAkaun, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
        $.unblockUI();
    }

    function bayarBilCukai(f,idHakmilik){
        var form = $(f).formSerialize();
        f.action = "${pageContext.request.contextPath}/hasil/kutipan_hasil?search&hakmilik.idHakmilik="+idHakmilik;
        f.submit();
    }
</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>
<table width="100%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">Pertanyaan Bil</font>
            </div>
        </td>
    </tr>
</table>
<s:form id="bilCukai" beanclass="etanah.view.stripes.hasil.PertanyaanBilCukaiActionBean">
    <div class="subtitle">
    <s:errors/>
    <s:messages/>
    <fieldset class="aras1">
        <legend>Carian</legend>
        <div class="instr-fieldset">
            <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                <em>PERINGATAN:</em>Sila Masukkan Salah Satu Maklumat Berikut
            </c:if>
            <c:if test="${actionBean.kodNegeri eq 'sembilan'}">
                <em>PERINGATAN:</em>Sila Masukkan Maklumat Berikut
            </c:if>
        </div>&nbsp;
        <c:if test="${actionBean.kodNegeri eq 'melaka'}">
            <p>
                <label>No. Akaun :</label>
                <s:text name="noAkaunSearch" size="30" maxlength="28" id="akaun" onchange="this.value = this.value.toUpperCase();"/>
            </p>
        </c:if>
        <p>
            <label>ID Hakmilik :</label>
            <s:text name="idHakmilikSearch" size="30" maxlength="28" id="hakmilik" onchange="this.value = this.value.toUpperCase();"/>
        </p>
        <table border="0" width="100%">
            <tr>
                <td align="center">
                    <s:submit name="Carian" value="Cari" class="btn" onclick="return carianHakmilik(this.form,this.name);"/>
                    <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('bilCukai');"/>
                </td>
            </tr>
        </table>
    </fieldset>
    </div>
    <c:if test="${actionBean.aktifPanel}">
         <div class="content" align="center">
           <fieldset class="aras1">
               <legend>Maklumat Hakmilik</legend>
               <div align="left">
                   <font size="2" color="black">Petunjuk :</font>
                    <p>
                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                             width="20" height="20" /> - <font size="2" color="black">Papar Bil</font>
                        &nbsp;<b>|</b>&nbsp;
                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                             width="20" height="20" /> - <font size="2" color="black">Cetak Bil</font>
                    </p>
               </div>
                <br>
               <display:table class="tablecloth" name="${actionBean.senaraiAkaun}" pagesize="10" cellpadding="0" cellspacing="0" id="line" requestURI="/hasil/pertanyaan_bil">
                   <display:column title="Bil" sortable="true"><div align="center">${line_rowNum}</div></display:column>
                   <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                        <display:column property="noAkaun" title="No. Akaun" />
                   </c:if>
                   <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                   <display:column property="hakmilik.daerah.nama" title="Daerah" />
                   <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim"/>
                   <%--<display:column title="Keluasan"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/> ${line.hakmilik.kodUnitLuas.nama}</display:column>--%>
                   <display:column property="pemegang.nama" title="Nama Pembayar"/>
                   <display:column title="Jumlah Perlu Bayar" style="text-align:right">RM <fmt:formatNumber pattern="#,##0.00" value="${line.baki}"/></display:column>
                   <%--<display:column property="hakmilik.kodStatusHakmilik.nama" title="Status" />--%>
                   <%--<display:column title="Tindakan"><s:button name="" value="Papar Bil" class="btn" onclick="paparBil(this.form, '${line.hakmilik.idHakmilik}');"/></display:column>--%>
                   <display:column title="Tindakan" style="width:220px">
                       <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                         onclick="paparBil(this.form, '${line.hakmilik.idHakmilik}');" height="30" width="30" alt="papar"
                         onmouseover="this.style.cursor='pointer';" title="Papar Bil untuk Hakmilik ${line.hakmilik.idHakmilik}"/>
                       &nbsp;<b>|</b>&nbsp;
                       <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                         onclick="cetakBilCukai(this.form, '${line.hakmilik.idHakmilik}','${line.noAkaun}');" height="30" width="30" alt="cetak"
                         onmouseover="this.style.cursor='pointer';" title="Cetak Bil untuk Hakmilik ${line.hakmilik.idHakmilik}"/>
                       <c:set value="false" var="X8A"/>
                       <c:forEach items="${actionBean.senaraiHakmilik}" var="listHakmilik">
                           <c:if test="${listHakmilik.idHakmilik eq line.hakmilik.idHakmilik}">
                               <c:set value="true" var="X8A"/>
                           </c:if>
                       </c:forEach>
                        <c:if test="${X8A eq 'false'}">
                            &nbsp;<b>|</b>&nbsp;
                            <s:button name="" value="Bayar" class="btn" onclick="bayarBilCukai(this.form,'${line.hakmilik.idHakmilik}');"/>
                        </c:if>
                   </display:column>
               </display:table>
           </fieldset>
         </div>
    </c:if>
</s:form>
