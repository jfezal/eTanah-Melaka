<%-- 
    Document   : maklumatBank
    Created on : Nov 29, 2012, 12:41:41 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kemaskini Maklumat Bank</title>
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
                    <div class="content" align="center">
                        <legend>Maklumat Bank</legend>
                        <table class="tablecloth" border="0">  
                        <tr>
                            <td><font color="red" size="4">*</font>Cara Bayar :</td>
                            <td>
                                <s:select name="permohonanPihakPendeposit.caraBayaran" id="caraBayar">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:option value="CT">Cek Tempatan</s:option>
                                    <s:option value="EF">Wang Dalam Pindahan</s:option>
                                    <s:option value="DB">Deraf Bank</s:option>
                                </s:select>
                            </td>
                        </tr>
                        <tr>
                            <td><font color="red" size="4">*</font>Jenis Bank :</td>
                            <td>
                                 <s:select name="permohonanPihakPendeposit.bank.kod" id="jenisBank">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodBank}" label="nama" value="kod"/>
                                </s:select>
                            </td>
                        </tr>
                        <tr>
                            <td><font color="red" size="4">*</font>No Akaun Bank :</td>
                            <td>
                                <s:text name="permohonanPihakPendeposit.noAkaun" id="noAkaun"  size="30"/> 
                            </td>
                        </tr>
                        <tr>
                            <td><font color="red" size="4">*</font>Amaun :</td>
                            <td>
                                <s:text name="permohonanPihakPendeposit.amaun" id="amaun" size="40"/> 
                            </td>
                        </tr>
                        <tr>
                            <td><font color="red" size="4">*</font>Catatan :</td>
                            <td>
                                <s:textarea name="permohonanPihakPendeposit.catatan" id="catatan" cols="60" rows="5"/>
                            </td>
                        </tr>
                        </table>
                            <br/><br/>
                            <div class="content" align="center">
                        <table class="tablecloth" border="0">        
                            <tr>
                                <td align="right" colspan="2">
                                    <%--<s:button name="simpanKandunganSempadan" id="save" value="Simpan" class="btn" onclick="saveSempadan(this.name, this.form);"/>--%>
                                    <s:submit name="kemaskiniKandunganBank" value="Kemaskini" class="btn" onclick="NoPrompt();"/>
                                    <s:button name="showEditSempadan" value="Isi Semula" class="btn" onclick="resetUlasan(this.name, this.form);" />
                                    <s:button name="tutup" value="Kembali" class="btn" onclick="openFrame('pendeposit')"/>
                                    <%--<s:button name="closeWindow" value="Tutup" class="btn" onclick="closeWindow123(this.name, this.form)" />--%> 
                                </td>
                            </tr>
                        </table>
                            </div>
                    </div>

                    <br/>
            </fieldset>
        </div>
    </s:form>
</body>