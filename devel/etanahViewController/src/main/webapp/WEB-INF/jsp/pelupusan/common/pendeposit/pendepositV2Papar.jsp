<%-- 
    Document   : pendepositV2Papar
    Created on : Dec 6, 2012, 12:35:12 PM
    Author     : Admin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Maklumat Pendeposit</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    
    $(document).ready(function() {
         
        //            maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

   
    }); //END OF READY FUNCTION
         
        
    
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }
    function openFrame(type){
        NoPrompt();
        var idHakmilik = $('#idHakmilik').val();
        //    alert(idHakmilik);
        window.open("${pageContext.request.contextPath}/pelupusan/pendepositV2?openFrame&idHakmilik="
            +idHakmilik+"&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
    function refreshpage(){
        NoPrompt();
    <c:choose>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                var idHakmilik = $('#idHakmilik').val();
                opener.refreshV2ManyHakmilik('main',idHakmilik);
        </c:when>
        <c:otherwise>
                opener.refreshV2('main');
        </c:otherwise>
    </c:choose>
            self.close();
        }

</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true; 
        window.onbeforeunload = WarnUser;
        function WarnUser()
        {   
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }
        
    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pelupusan.common.PendepositV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>

        <div class="subtitle" id="main">
            <fieldset class="aras1">
                <s:hidden name="idHakmilik" id="idHakmilik"/>
                <s:hidden name="idPihakPendeposit" id="idPihakPendeposit"/>
                <div id="maklumatpendeposit">
                    <legend>Maklumat Pendeposit</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">
                            <tr>
                                <td>Nama :</td>
                                <td>
                                    <font color="black">
                                    ${actionBean.permohonanPihakPendeposit.pihak.nama}
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Jenis Pengenalan :
                                </td>
                                <td>
                                    <font color="black">
                                    ${actionBean.permohonanPihakPendeposit.pihak.jenisPengenalan.nama}
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <td>No Pengenalan :</td>
                                <td>
                                    <font color="black">
                                    ${actionBean.permohonanPihakPendeposit.pihak.noPengenalan}
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <td>Alamat :</td>
                                <td>
                                    <font color="black">
                                    ${actionBean.permohonanPihakPendeposit.pihak.alamat1}
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    <font color="black">
                                    ${actionBean.permohonanPihakPendeposit.pihak.alamat2}
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    <font color="black">
                                    ${actionBean.permohonanPihakPendeposit.pihak.alamat3}
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    <font color="black">
                                    ${actionBean.permohonanPihakPendeposit.pihak.alamat4}
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <td>Poskod :</td>
                                <td>
                                    <font color="black">
                                    ${actionBean.permohonanPihakPendeposit.pihak.poskod}
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <td>Negeri :</td>
                                <td>
                                    <font color="black">
                                    ${actionBean.permohonanPihakPendeposit.pihak.negeri.nama}
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <td>No Telefon :</td>
                                <td>
                                    <font color="black">
                                    ${actionBean.permohonanPihakPendeposit.pihak.noTelefon1}
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <td>Emel :</td>
                                <td>
                                    <font color="black">
                                    ${actionBean.permohonanPihakPendeposit.pihak.email}
                                    </font>
                                </td>
                            </tr>
                        </table>
                    </div>                   
                        <legend>Maklumat Bank</legend>
                        <div class="content" align="center">
                        <table class="tablecloth" border="0">  
                            <tr>
                                <td>Cara Bayar :</td>
                                <td>
                                    <font color="black">
                                        ${actionBean.permohonanPihakPendeposit.caraBayaran} - 
                                        <c:if test="${actionBean.permohonanPihakPendeposit.caraBayaran eq 'CT'}">
                                            Cek Tempatan
                                        </c:if>
                                        <c:if test="${actionBean.permohonanPihakPendeposit.caraBayaran eq 'EF'}">
                                            Wang Dalam Pindahan
                                        </c:if>
                                        <c:if test="${actionBean.permohonanPihakPendeposit.caraBayaran eq 'DB'}">
                                            Deraf Bank
                                        </c:if>
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <td>Jenis Bank :</td>
                                <td>
                                    <font color="black">
                                    ${actionBean.permohonanPihakPendeposit.bank.nama}
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <td>No Akaun Bank :</td>
                                <td>
                                    <font color="black">
                                    ${actionBean.permohonanPihakPendeposit.noAkaun}
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <td>Amaun :</td>
                                <td>
                                    <font color="black">
                                    RM <s:format value="${actionBean.permohonanPihakPendeposit.amaun}" formatPattern="#,##0.00"/>
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <td>Catatan :</td>
                                <td>
                                    <font color="black">
                                    <s:textarea name="${actionBean.permohonanPihakPendeposit.catatan}" value="${actionBean.permohonanPihakPendeposit.catatan}" id="catatan" readonly="true" cols="60" rows="5"/>
                                    </font>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">        
                            <tr>
                                <td align="right" colspan="2">
                                    <%--<s:button name="simpanKandunganSempadan" id="save" value="Simpan" class="btn" onclick="saveSempadan(this.name, this.form);"/>--%>
                                    <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                                    <%--<s:button name="closeWindow" value="Tutup" class="btn" onclick="closeWindow123(this.name, this.form)" />--%> 
                                </td>
                            </tr>
                        </table>
                    </div>
                    <br/>
                </div>
            </fieldset>
        </div>
    </s:form>
</body>


