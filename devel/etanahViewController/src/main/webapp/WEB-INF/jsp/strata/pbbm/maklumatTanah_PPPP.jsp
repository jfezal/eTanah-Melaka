<%--
    Document   : maklumatTanahPPPP
    Created on : Oct 12, 2012, 10:42:01 AM
    Author     : murali
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<style type="text/css">
    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }
</style>
<script type="text/javascript">    
    function popUpHakmilik(w){
    <%--alert("popup"+w);--%>
            var url = '${pageContext.request.contextPath}/strata/maklumat_tanah_phpp?showFormPopup&idHakmilik='+w;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1500,height=800");
        }

        function perincianHakmilik(v){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });

            $.get("${pageContext.request.contextPath}/strata/maklumat_tanah_phpp?showHakmilikStrata&idHakmilik="+v,
            function(data){
                //alert(data);
                $("#perincianHakmilik").show();
                $("#perincianHakmilik").html(data);
                $.unblockUI();
            });

        }
</script>
<s:form beanclass="etanah.view.strata.MaklumatTanahPHPPActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik 
            </legend>
            <center><p><font color="blue"><b>Jumlah Unit Syer: <s:text name="jumlahUnitSyor" readonly="true" size="8">${actionBean.jumlahUnitSyor}</s:text>
                        </b></font></p></center>             
                        <c:if test="${fn:length(actionBean.idStrataList) > 0}">
                <p><label></label>
                    <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.idStrataList)}" id="sizeHakmilikPermohonan"/>
                    <display:table class="tablecloth" style="width:100%;" requestURI="/strata/maklumat_hakmilik_permohonan" name="${actionBean.idStrataList}" cellpadding="0" cellspacing="0" pagesize="400" id="line">
                        <display:column title="No" sortable="true"><center>${line_rowNum}</center></display:column>
                    <display:column title="ID Hakmilik" ><center><a href="#" onclick="perincianHakmilik('${line.idHakmilik}');return false;">${line.idHakmilik}</a></center>
                        <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.idHakmilik}"/>
                    </display:column>
                    <display:column title="No Bangunan">
                        <center>${line.noBangunan}</center>
                    </display:column>
                    <display:column title="No Tingkat">
                        <center>${line.noTingkat}</center>
                    </display:column>
                    <display:column title="No Petak">
                        <center>${line.noPetak}</center>
                    </display:column>
                    <%--<display:column title="Luas / Unit">
                        <center><s:text name="idStrataList[${line_rowNum-1}].luas" id="luas${line_rowNum-1}" value="${line.luas}" formatPattern="###0.0000" readonly="true" size="15" onchange="kiraCukaiKelompok('${line.idHakmilik}','${line_rowNum-1}');"/>
                            <s:text name="idStrataList[${line_rowNum-1}].kodUnitLuas.nama" id="kodUOM${line_rowNum-1}" value="${line.kodUnitLuas.nama}" readonly="true" size="25" onchange="kiraCukaiKelompok('${line.idHakmilik}','${line_rowNum-1}');"/>
                        </center>
                    </display:column>--%>
                </display:table>
                &nbsp;
                </p>
            </c:if> 
        </fieldset>
    </div>
    <div id="perincianHakmilik">
    </div>
</s:form>