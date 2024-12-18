<%-- 
    Document   : LampiranA_popupNyata_SBMS_TSPSS
    Created on : Feb 26, 2014, 12:16:07 PM
    Author     : khairul.hazwan
--%>


<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<script type="text/javascript">
   
        
        function hapusSekatanKepentingan(val,idPlot, foredit){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pembangunan/melaka/rencanaRingkasanLampiranAForTPSSSnSBMS?hapusSekatanKepentingan&idMppSekatan='+val+'&idPlot=' + idPlot + '&foredit=' + foredit;
                $.get(url,
                function(data){
                    $('#refresh').html(data);
                },'html');
//                location.reload(true);
            }
        }
        
        function hapusSyaratNyata(val,idPlot, foredit){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pembangunan/melaka/rencanaRingkasanLampiranAForTPSSSnSBMS?hapusSyaratNyata&idMppSyarat='+val+'&idPlot=' + idPlot + '&foredit=' + foredit;
                $.post(url,
                function(data){
                    $('#refresh').html(data);
                },'html');
//                location.reload(true);
            }
        }
    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric(strString)
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for (intIndex = 0; intIndex < strString.length; intIndex++)
        {
            strBuffer = strString.substr(intIndex, 1);
            // Is this a number
            if (strValidCharacters.indexOf(strBuffer) > -1)
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function searchKodSyaratNyata(idPlot, foredit) {
        var url = '${pageContext.request.contextPath}/pembangunan/melaka/rencanaRingkasanLampiranAForTPSSSnSBMS?showFormCariKodSyaratNyata&idPlot=' + idPlot + '&foredit=' + foredit;
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=1000px,height=1000px,scrollbars=yes");
    }

    function searchKodSekatan(idPlot, foredit) {
        var url = '${pageContext.request.contextPath}/pembangunan/melaka/rencanaRingkasanLampiranAForTPSSSnSBMS?showFormCariKodSekatan&idPlot=' + idPlot + '&foredit=' + foredit;
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=900px,height=700px,scrollbars=yes");
    }

</script>

<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>         
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pembangunan.RencanaRingkasanLampiranAForTSPSSnSBMSActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">

            <div id ="refresh">
                <table border="0" width="85%" align="center" cellspacing="5">
                    <s:hidden name="idPermohonan" value="${actionBean.idPermohonan}"/>
                    <s:hidden name="idPlot" value="${actionBean.idPlot}"/>
                    <s:hidden name="foredit" value="${actionBean.foredit}"/>
                    <s:hidden name="kodPemilikan" value="${actionBean.kodPemilikan}"/>

                    <c:if test = "${actionBean.foredit eq 'NY'}">                                   
                        <tr>
                           
                            <td align="center">
                                <display:table class="tablecloth" name="${actionBean.listSyaratNyata}"
                                               id="line">
                                    <display:column title="Bil">${line_rowNum}</display:column>
                                    <display:column title="Syarat Nyata " style="width:1000px"> ${line.kodSyaratNyata.syarat}</display:column>
                                    <display:column title="Kemaskini">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="hapusSyaratNyata('${line.idMppSyarat}','${actionBean.idPlot}','${actionBean.foredit}')" onmouseover="this.style.cursor='pointer';">
                                        </div>
                                    </display:column>
                                </display:table>

                            </td>
                        </tr>
                    </c:if>

                    <c:if test = "${actionBean.foredit eq 'SK'}">  
                        <tr>
                            <td align="center">
                                <display:table class="tablecloth" name="${actionBean.listSekatan}"
                                               id="line">
                                    <display:column title="Bil">${line_rowNum}</display:column>
                                    <display:column title="Sekatan Kepentingan " style="width:1000px"> ${line.kodSekatanKepentingan.sekatan}</display:column>
                                    <display:column title="Kemaskini">
                                       <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="hapusSekatanKepentingan('${line.idMppSekatan}','${actionBean.idPlot}','${actionBean.foredit}')" onmouseover="this.style.cursor='pointer';">
                                        </div>
                                    </display:column>
                                </display:table>

                            </td>
                        </tr>
                    </c:if>
                </table>


                <br><br>         
                <tr>
                <div align="center">
                    <c:if test = "${actionBean.foredit eq 'NY'}">  
                        <td align="center"><s:button class="btn" value="Cari" name="add" onclick="javascript:searchKodSyaratNyata('${actionBean.idPlot}','${actionBean.foredit}')" /></td>
                    </c:if>
                    <c:if test = "${actionBean.foredit eq 'SK'}">  
                        <td align="center"><s:button class="btn" value="Cari" name="add" onclick="javascript:searchKodSekatan('${actionBean.idPlot}','${actionBean.foredit}')" /></td>
                    </c:if>
                    <td><s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/></td>                            
                </div>
                </tr>
            </div>
        </fieldset></div>
        </s:form>

