<%--
    Document   :  laporan_tanahV2AddEditLotSmpdn.jsp
    Created on :  March 05, 2012, 11:28:13 AM
    Author     :  Shazwan
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PERIHAL LOT-LOT BERSEMPADAN</title>
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
        $('#noLot').hide();
        $('#idhakmilik').hide();
        
    <c:choose>
        <c:when test="${!empty actionBean.disLaporanTanahSempadan.statusSempadan}">
            <c:if test="${!empty actionBean.disLaporanTanahSempadan.noLot}">
                    document.form.noLotChecked.checked=true;
                    $('#noLot').show();
            </c:if>
            <c:if test="${!empty actionBean.disLaporanTanahSempadan.idHakmilikSmpdn}">
                    document.form.idHakmilikChecked.checked=true;
                    $('#idhakmilik').show();
            </c:if>
        </c:when>    
        <c:otherwise>
                document.form.noLotChecked.checked=false; 
                document.form.idHakmilikChecked.checked=false;
                $('#noLot').hide();
                $('#idhakmilik').hide();
        </c:otherwise>
    </c:choose> 
   
        }); //END OF READY FUNCTION
         
        
    
        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }
        function openLot(checkboxVal){
            if(checkboxVal.checked)
                $('#noLot').show();
            else
                $('#noLot').hide();
        }
        function pilih(){
            var milikKerajaan = $('#milikKerajaan').val();
            if(milikKerajaan=="Y"){
                $('#jenisTanah').hide();
            }else{
                $('#jenisTanah').show();
            }
        }
        function openHakmilik(checkboxVal){
            if(checkboxVal.checked)
                $('#idhakmilik').show();
            else
                $('#idhakmilik').hide();
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
            window.open("${pageContext.request.contextPath}/penguatkuasaan/laporan_tanahV2?openFrame&idHakmilik="
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
    <s:form beanclass="etanah.view.penguatkuasaan.LaporanTanahV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="disLaporanTanahSempadan.statusSempadan"/>

        <div class="subtitle" id="main">
            <fieldset class="aras1">
                <s:hidden name="idHakmilik" id="idHakmilik"/>
                <div id="perihaltanah">
                    <legend>Perihal Lot-Lot Bersempadan</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">
                            <tr>
                                <td><font color="red" size="4">*</font>Sempadan :</td>
                                <td>
                                    <c:if test="${empty actionBean.disLaporanTanahSempadan.statusSempadan}">
                                        <s:select name="index" id="index" >
                                            <s:option value="U">Utara</s:option>
                                            <s:option value="S">Selatan</s:option>
                                            <s:option value="T">Timur</s:option>
                                            <s:option value="B">Barat</s:option>
                                            <%--<s:options-collection collection="${list.senaraiKegunaanTanah}" label="nama" value="kod"/>--%>
                                        </s:select>
                                    </c:if>
                                    <c:if test="${!empty actionBean.disLaporanTanahSempadan.statusSempadan}">
                                        <s:hidden name="index" value="${actionBean.disLaporanTanahSempadan.jenisSmpdn}"/>
                                        <c:choose>
                                            <c:when test="${actionBean.disLaporanTanahSempadan.jenisSmpdn eq 'U'}">
                                                Utara
                                            </c:when>
                                            <c:when test="${actionBean.disLaporanTanahSempadan.jenisSmpdn eq 'S'}">
                                                Selatan
                                            </c:when>
                                            <c:when test="${actionBean.disLaporanTanahSempadan.jenisSmpdn eq 'T'}">
                                                Timur
                                            </c:when>
                                            <c:when test="${actionBean.disLaporanTanahSempadan.jenisSmpdn eq 'B'}">
                                                Barat
                                            </c:when>
                                        </c:choose>

                                        <%--<s:select name="index" id="index" value="${actionBean.disLaporanTanahSempadan.jenisSmpdn}">
                                            <s:option value="U">Utara</s:option>
                                            <s:option value="S">Selatan</s:option>
                                            <s:option value="T">Timur</s:option>
                                            <s:option value="B">Barat</s:option>
                                        </s:select>--%>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Milik :</td>
                                <td>
                                    <c:if test="${empty actionBean.disLaporanTanahSempadan.statusSempadan}">
                                        <s:select name="disLaporanTanahSempadan.milikKerajaan" id="milikKerajaan" >
                                            <s:option value="T">Milik</s:option>
                                            <s:option value="Y">Kerajaan</s:option>
                                            <s:option value="R">Rizab</s:option>
                                            <%--<s:options-collection collection="${list.senaraiKegunaanTanah}" label="nama" value="kod"/>--%>
                                        </s:select>
                                    </c:if>
                                    <c:if test="${!empty actionBean.disLaporanTanahSempadan.statusSempadan}">
                                        <s:select name="disLaporanTanahSempadan.milikKerajaan" id="milikKerajaan" value="${actionBean.disLaporanTanahSempadan.milikKerajaanSmpdn}">
                                            <s:option value="T">Milik</s:option>
                                            <s:option value="Y">Kerajaan</s:option>
                                            <s:option value="R">Rizab</s:option>
                                            <%--<s:options-collection collection="${list.senaraiKegunaanTanah}" label="nama" value="kod"/>--%>
                                        </s:select>
                                    </c:if>

                                </td>
                            </tr>
                            <tr id="jenisTanah">
                                <td>
                                    Jenis Tanah :
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty actionBean.disLaporanTanahSempadan.statusSempadan}">
                                            <s:checkbox title="No Lot" name="jenisTanah" checked="true" value="lot" onclick="openLot(this)"/>&nbsp;No Lot&nbsp;
                                            <s:checkbox name="jenisTanah" value="hakmilik" onclick="openHakmilik(this)"/>&nbsp;ID Hakmilik
                                        </c:when>
                                        <c:when test="${!empty actionBean.disLaporanTanahSempadan.statusSempadan}">
                                            <s:checkbox id="noLotChecked" title="No Lot" name="noLotChecked" value="lot" onclick="openLot(this)"/>&nbsp;No Lot&nbsp;
                                            <s:checkbox id="idHakmilikChecked" name="idHakmilikChecked" value="hakmilik" checked="true" onclick="openHakmilik(this)"/>&nbsp;ID Hakmilik
                                        </c:when>    
                                        <c:otherwise>

                                        </c:otherwise>
                                    </c:choose>

                                </td>
                            </tr>
                            <tr id="noLot">
                                <td><font color="red" size="4">*</font>No Lot :</td>
                                <td>
                                    <s:select name="disLaporanTanahSempadan.kodLot" value="${actionBean.disLaporanTanahSempadan.kodLot}">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiLotPelupusan}" label="nama" value="kod" />
                                    </s:select>
                                    <s:text name="disLaporanTanahSempadan.noLot" onkeyup="validateNumber(this,this.value);" id="noLot"/>&nbsp;
                                </td>
                            </tr>
                            <tr id="idhakmilik">
                                <td><font color="red" size="4">*</font>ID. Hakmilik</td>
                                <td>
                                    <c:if test="${empty actionBean.disLaporanTanahSempadan.statusSempadan}">
                                        <s:text name="disLaporanTanahSempadan.idHakmilikSmpdn"/>
                                    </c:if>
                                    <c:if test="${!empty actionBean.disLaporanTanahSempadan.statusSempadan}">                            
                                        ${actionBean.disLaporanTanahSempadan.idHakmilikSmpdn}
                                        <s:hidden name="disLaporanTanahSempadan.idHakmilikSmpdn"/>
                                    </c:if>                        
                                </td>
                            </tr>

                            <tr>
                                <td><font color="red" size="4">*</font>Kegunaan Tanah :</td>
                                <td>
                                    <c:if test="${empty actionBean.disLaporanTanahSempadan.statusSempadan}">
                                        <s:textarea name="disLaporanTanahSempadan.kegunaan" id="kegunaan" rows="5" cols="80"></s:textarea>
                                    </c:if>
                                    <c:if test="${!empty actionBean.disLaporanTanahSempadan.statusSempadan}">
                                        <s:textarea name="disLaporanTanahSempadan.kegunaan" id="kegunaan" rows="5" cols="80" value="${actionBean.disLaporanTanahSempadan.kegunaanSmpdn}"></s:textarea>
                                    </c:if>                        
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Keadaan Tanah :</td>
                                <td>
                                    <c:if test="${empty actionBean.disLaporanTanahSempadan.statusSempadan}">
                                        <s:textarea name="disLaporanTanahSempadan.keadaanTanah" id="keadaanTanah" rows="5" cols="80"></s:textarea>
                                    </c:if>
                                    <c:if test="${!empty actionBean.disLaporanTanahSempadan.statusSempadan}">
                                        <s:textarea name="disLaporanTanahSempadan.keadaanTanah" id="keadaanTanah" rows="5" cols="80" value="${actionBean.disLaporanTanahSempadan.keadaanTanah}"></s:textarea>
                                    </c:if>                        
                                </td>
                            </tr>
                            <tr>
                                <td>Jarak Dari Tanah Dipohon :</td>
                                <td>
                                    <c:if test="${empty actionBean.disLaporanTanahSempadan.jarakDariTanah}">
                                        <s:text name="disLaporanTanahSempadan.jarakDariTanah" id="jarakDariTanah" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);"></s:text>
                                        <s:select name="disLaporanTanahSempadan.jarakDariTanahUOM" id="jarakDariTanahUOM" >
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.kodUOMByJarak}" label="nama" value="kod"/>
                                        </s:select>
                                    </c:if>
                                    <c:if test="${!empty actionBean.disLaporanTanahSempadan.jarakDariTanah}">
                                        <s:text name="disLaporanTanahSempadan.jarakDariTanah" id="jarakDariTanah" formatPattern="#,###,##0.0000" maxlength="15" onkeyup="validateNumber(this,this.value);" value="${actionBean.disLaporanTanahSempadan.jarakDariTanah}"></s:text>
                                        <s:select name="disLaporanTanahSempadan.jarakDariTanahUOM" id="jarakDariTanahUOM" value="${actionBean.disLaporanTanahSempadan.jarakDariTanahUOM}">
                                            <s:option value="0">Sila Pilih</s:option>
                                            <s:options-collection collection="${listUtil.kodUOMByJarak}" label="nama" value="kod"/>
                                        </s:select>
                                    </c:if>

                                </td>
                            </tr>
                            <tr>
                                <td>Catatan :</td>
                                <td>
                                    <c:if test="${empty actionBean.disLaporanTanahSempadan.statusSempadan}">
                                        <s:textarea name="disLaporanTanahSempadan.catatan" id="catatan" rows="5" cols="80"></s:textarea>
                                    </c:if>
                                    <c:if test="${!empty actionBean.disLaporanTanahSempadan.statusSempadan}">
                                        <s:textarea name="disLaporanTanahSempadan.catatan" id="catatan" rows="5" cols="80" value="${actionBean.disLaporanTanahSempadan.catatan}"></s:textarea>
                                    </c:if>

                                </td>
                            </tr>
                            <tr>
                                <td align="right" colspan="2">
                                    <%--<s:button name="simpanKandunganSempadan" id="save" value="Simpan" class="btn" onclick="saveSempadan(this.name, this.form);"/>--%>
                                    <c:if test="${empty actionBean.disLaporanTanahSempadan.statusSempadan}">
                                        <s:submit name="simpanKandunganSempadan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                        <s:button name="showEditSempadan" value="Isi Semula" class="btn" onclick="resetUlasan(this.name, this.form);" />
                                    </c:if>
                                    <c:if test="${!empty actionBean.disLaporanTanahSempadan.statusSempadan}">   
                                        <s:submit name="simpanKandunganSempadan" value="Kemaskini" class="btn" onclick="NoPrompt();"/>
                                    </c:if>
                                    <s:button name="tutup" value="Kembali" class="btn" onclick="openFrame('lSempadan')"/>
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

