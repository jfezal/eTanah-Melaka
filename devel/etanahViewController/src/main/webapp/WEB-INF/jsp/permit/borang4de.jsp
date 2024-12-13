<%-- 
    Document   : borang4de
    Created on : Sep 7, 2015, 1:05:47 PM
    Author     : Hazwan
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
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
    
    function carianBayaran(id,jb,noPermit){          
        window.open("${pageContext.request.contextPath}/permitEntry?carianBayaranPopup&noFail=" + id +"&jenisBorang=" + jb + "&noPermit=" + noPermit, "eTanah",
        "status=0,toolbar=0,location=no,menubar=0,width=910,height=350,scrollbars=yes");
    }
    
    function carianPemohon(id,jb,noPermit){          
        window.open("${pageContext.request.contextPath}/permitEntry?carianPihakPopup&noFail=" + id +"&jenisBorang=" + jb + "&noPermit=" + noPermit, "eTanah",
        "status=0,toolbar=0,location=no,menubar=0,width=910,height=350,scrollbars=yes");
    }
    
    function validateForm() {
        if ($("#kodPermit").val() == "") {
            alert('Sila Pilih Kod Permit !!!');        
            return false;
        }else if ($("#noPermit").val() == "") {
            alert('Sila Masukkan No Permit !!!');
            return false;
        }else if ($("#daerah").val() == "") {
            alert('Sila Pilih Daerah !!!');
            return false;
        }else if ($("#noResit").val() == "") {
            alert('Sila Masukkan No. Resit !!!');
            return false;
        }else if ($("#bayaran").val() == "") {
            alert('Sila Masukkan Bayaran !!!');
            return false;
        }else if ($("#nama").val() == "") {
            alert('Sila Masukkan Nama Pemegang Permit !!!');
            return false;
        }else if ($("#alamat").val() == "") {
            alert('Sila Masukkan Alamat Pemegang Permit !!!');
            return false;
        }else if ($("#noPengenalan").val() == "") {
            alert('Sila Masukkan No. Pengenalan Pemegang Permit !!!');
            return false;
        }else if ($("#tarikhPermit").val() == "") {
            alert('Sila Masukkan Tarikh Permit Dikeluarkan !!!');
            return false;
        }else {
            return true;
        }
    }
    
    function validatePerihal() {
        if ($("#bandar").val() == "") {
            alert('Sila Pilih Bandar/Pekan/Mukim !!!');        
            return false;
        }else if ($("#tanah").val() == "") {
            alert('Sila Pilih Jenis Tanah !!!');        
            return false;
        }else if ($("#luas").val() == "") {
            alert('Sila Masukkan Luas !!!');
            return false;
        }else {
            return true;
        }
    }
    
    function validateSyarat() {
        if ($("#tarikhMula").val() == "") {
            alert('Sila Pilih Tarikh Mula !!!');     
            return false;
        }else if ($("#tarikhtamat").val() == "") {
            alert('Sila Pilih Tarikh Tamat !!!');      
            return false;
        }else if ($('#jenisStruktur').val() == "") {
            alert('Sila Masukkan Jenis Struktur  !!!');    
            return false;
        }else if ($("#lokasi").val() == "") {
            alert('Sila Masukkan Lokasi  !!!');  
            return false;
        }else{
            return true;           
        }
    }
    
    function refreshPihak(nama,alamat1,alamat2,alamat3,alamat4,poskod,negeri,noPengenalan){
        $('#nama').val(nama);
        $('#alamat1').val(alamat1); 
        $('#alamat2').val(alamat2); 
        $('#alamat3').val(alamat3); 
        $('#alamat4').val(alamat4); 
        $('#poskod').val(poskod); 
        $('#negeri').val(negeri); 
        $('#noPengenalan').val(noPengenalan);                
    }
        
    function refreshBayaran(noResit,amaun){
        $('#noResit').val(noResit);
        $('#bayaran').val(amaun);       
    }
    
    function upload(id,jb) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/permitEntry?uploadDoc&noFail='+ id+'&jenisBorang='+jb;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=300,scrollbars=yes, left=" + left + ",top=" + top);
    }
    
    function showReport(id,reportName){
        var noPermit = '${actionBean.noPermit}';
        var url = '${pageContext.request.contextPath}/reportGeneratorServlet?reportName='+reportName+'&report_p_id_mohon='+id+'&report_p_no_permit='+noPermit;  
        window.open(url, "eTanah","status=0,toolbar=0,location=no,menubar=0,width=900px,height=700px,scrollbars=yes");
    }      
    
    function validateTt() {
        if ($("#tandatangan").val() == "") {
            alert('Sila Pastikan Penama Tandatangan Telah Dipilih !!!');     
            return false;
        }else{
            return true;           
        }
    }
    
    function changeFormat2(objId){
        objId = '#' + objId;
        var val = $(objId).val();
        if (isNaN( parseFloat(val))) $(objId).val("0.00");
        else $(objId).val(parseFloat(val).toFixed(2));
    }
    
    function zeroPad(num,count)
    {
        var numZeropad = num + '';
        while(numZeropad.length < count) {
            numZeropad = "0" + numZeropad;
        }
        return numZeropad;
    }
    
    function formatNoLot(){
        var num = $('#noLot').val();
        var noLot = zeroPad(num,'7');
        $('#noLot').val(noLot);
    }
    
</script>
<s:useActionBean beanclass = "etanah.view.ListUtil" var="list"/>
<stripes:form beanclass="etanah.view.stripes.permit.PermitActionBean">
    <s:messages/>
    <s:hidden name="noFail" id="noFail" />
    <s:hidden name="jenisBorang" id="jenisBorang" />  
    <s:hidden name="noPermit" id="noPermit" />
    <s:hidden name="kodPermit" id="kodPermit" />

    <div class="subtitle">
        <fieldset class="aras1">

            <legend>
                <td align="center"><font color="#001848">Kanun Tanah Negara</font></td>
            </legend>
            <br/>
            <div align="center"><font color="#001848"><b> Borang 4De</b></font></div>
            <br/>
            <div align="center"><font color="#001848"><b> (Jadual Keenam Belas)</b></font></div>
            <br/>

            <div align ="center">
                <div align="center"><font color="#001848"><b>PERMIT BAGI PENGGUNAAN RUANG UDARA ATAS</b></font></div> 

                <td>
                    <s:radio name="radio1" id="radio1" value="K"/><font color="#001848"> TANAH KERAJAAAN &nbsp;</font>
                    <s:radio name="radio1" id="radio1" value="R"/><font color="#001848"> TANAH RIZAB </font>                
                </td>             
            </div>
            <br/>       

            <div class="content" align="center">
                <table border="0" width="50%">
                    <tr>
                        <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. Fail</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                        <td>
                            ${actionBean.noFail}
                            <%--<s:text name="noFail" id="noFail" size="12" style="width:200px;" maxlength="30" readonly="true"/>--%>
                        </td>
                    </tr>
                    <%--
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Kod Permit</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                        <td>
                            <s:text name="kodPermit" id="kodPermit" size="12" style="width:200px;" maxlength="30" readonly="true"/>                         
                        </td>
                    </tr>  
                    --%>
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. Permit</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                        <td>${actionBean.kodPermit}  ${actionBean.noPermit}</td>
                        <%--<s:text name="noPermit" id="noPermit" size="12" style="width:200px;" maxlength="30" readonly="true"/>--%>
                    </tr>   
                    <tr>
                        <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Daerah</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                        <td>
                            <s:select name="daerah" id="daerah" style="width:200px;">
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodDaerah}" label="nama" value="kod" />
                            </s:select>
                        </td>
                    </tr>
                </table>
            </div>

            <div class="content" align="center" id="bayar">
                <table border="0" width="50%">
                    <tr>
                        <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. Resit</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                        <td>
                            <s:text name="noResit" id="noResit" size="12" style="width:200px;" maxlength="30"/> &nbsp;
                            <s:button class="btn" name="carian" value="Carian Bayaran"  onclick="carianBayaran('${actionBean.noFail}','${actionBean.jenisBorang}','${actionBean.noPermit}');" />    
                        </td>
                    </tr>
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Fi (RM)</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                        <td><s:text name="bayaran"  formatPattern="#,#.00" onkeyup="validateNumber(this,this.value);" size="12" id="bayaran" style="width:200px;" onblur="changeFormat2('bayaran')"/></td>
                    </tr> 
                </table>
            </div>

            <div class="content" align="center">
                <div id ="pihak">
                    <table border="0" width="50%">
                        <tr>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Nama Pemegang Permit</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                            <td>
                                <s:text name="nama" id="nama" size="12" style="width:200px;" maxlength="60" onblur="this.value=this.value.toUpperCase();"/>
                                <s:button class="btn" name="carian" value="Carian Pemohon"  onclick="carianPemohon('${actionBean.noFail}','${actionBean.jenisBorang}','${actionBean.noPermit}');" />  
                            </td>
                        </tr>
                        <tr>
                            <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Alamat</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                            <td><s:text name="alamat1" id="alamat1" size="12" style="width:200px;" maxlength="30" onblur="this.value=this.value.toUpperCase();"/></td>                                    
                        </tr>
                        <tr>
                            <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"></font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"></font> </td>                      
                            <td><s:text name="alamat2" id="alamat2" size="12" style="width:200px;" maxlength="30" onblur="this.value=this.value.toUpperCase();"/></td>                                         
                        </tr>
                        <tr>
                            <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"></font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"></font> </td>                       
                            <td><s:text name="alamat3" id="alamat3" size="12" style="width:200px;" maxlength="30" onblur="this.value=this.value.toUpperCase();"/></td>                                           
                        </tr>
                        <tr>
                            <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"></font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"></font> </td>                     
                            <td><s:text name="alamat4" id="alamat4" size="12" style="width:200px;" maxlength="30" onblur="this.value=this.value.toUpperCase();"/></td>                          
                        </tr>
                        <tr>
                            <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Poskod</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>                           
                            <td><s:text name="poskod" id="poskod" size="12" style="width:200px;" maxlength="5" onkeyup="validateNumber(this,this.value);"/></td>                          
                        <tr>
                            <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Negeri</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>      
                            <td>
                                <s:select name="negeri" id="negeri" style="width:200px;" >
                                    <s:option value="">Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                                </s:select>
                            </td>
                        </tr>
                        <tr>
                            <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. KP</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                            <td><s:text name="noPengenalan" id="noPengenalan" size="12" style="width:200px;" maxlength="30" onblur="this.value=this.value.toUpperCase();"/></td>
                        </tr>  
                    </table>
                </div>
            </div>

            <br></br>
            <div class="content" align="center">
                <tr>
                <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                    adalah dengan ini, diberi kebenaran untuk  menggunakan ruang udara atas tanah  yang diperihalkan di bawah
                    ini bagi maksud mendirikan, menyenggara dan menduduki *struktur-struktur/sebagaimana
                    yang diluluskan oleh Pihak Berkuasa Negeri, tertakluk kepada syarat-syarat yang dinyatakan di
                    bawah dan kepada peruntukan yang ditetapkan oleh Kaedah-Kaedah.
                </font>
                </tr> 
            </div>
            <br/>

            <div class="content" align="center">
                <table border="0" width="50%">                    
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Dikeluarkan Pada </font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                        <td><s:text name="tarikhPermit" id="tarikhPermit" class="datepicker" size="12" style="width:130px;" formatPattern="dd/MM/yyyy"/></td>
                    </tr> 
                </table>
            </div>
            <br/>

            <div class="content" align="center">
                <p>
                    <s:submit name="simpanPermitAsas" id="save" value="Simpan" class="btn" onclick="return validateForm();"/>&nbsp;
                </p>

                <!--<table border="0" width="10%" size="5"><tr><em>* Sila Pastikan Maklumat Di Atas Telah Disimpan</em></tr></table>-->
            </div>

    </div>
</fieldset>
</div>

<%--
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            <div align="center"><font color="#001848"><b> TANDATANGAN</b></font></div>
        </legend>
        <div class="content" align="center">
            <table border="0" width="35%">
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Tandatangan </font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                    <td>
                        <s:select name="tandatangan" id="tandatangan" style="width:300px;" >
                            <s:option value="">-- Sila Pilih --</s:option>
                            <s:options-collection collection="${actionBean.listPp}" value="idPengguna" label="nama" />
                        </s:select>
                    </td>
                </tr>
            </table>
        </div>
        <br/>

        <div class="content" align="center">
            <p>
                <s:submit name="simpanTandatangan" id="save" value="Simpan" class="btn" onclick="return validateTt();"/>&nbsp;
            </p>
            <table border="0" width="10%" size="5"><tr><em>* Sila Pastikan Penama Tandatangan Telah Disimpan</em></tr></table>
        </div>
    </fieldset>
</div>
<br/>
--%>

<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            <div align="center"><font color="#001848"><b> PERIHAL MENGENAI TANAH </b></font></div>
        </legend>
        <div class="content" align="center">
            <table border="0" width="35%">
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Bandar/Pekan/Mukim</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                    <td>
                        <s:select name="bandar" id="bandar" style="width:200px;" >
                            <s:option value="">Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodBandarMukim}" label="nama" value="kod" />
                        </s:select>
                    </td>
                </tr>
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Tanah Kerajaan/Tanah Rizab</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                    <td>
                        <s:select name="tanah" id="tanah" style="width:200px;" >
                            <s:option value="">Pilih</s:option>
                            <s:options-collection collection="${list.kodPemilikanForRAndKOnly}" label="nama" value="kod" />
                        </s:select>
                    </td>
                </tr>
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Kod Lot/PT</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                    <td>
                        <s:select name="kodLot" id="kodLot" style="width:200px;" >
                            <s:option value="">Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodLot}" label="nama" value="kod" />
                        </s:select>
                    </td>
                </tr>
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No Lot/PT</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                    <td><s:text name="noLot" id="noLot" size="12" style="width:200px;" maxlength="7" onblur="formatNoLot()"/></td>
                </tr>
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Tempat</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                    <td><s:text name="tempat" id="tempat" size="12" style="width:200px;" maxlength="30"/></td>
                </tr>
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Luas Tanah Untuk Diduduki</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                    <td><s:text name="luas" id="luas" size="12" style="width:200px;" maxlength="30"/></td>                       
                </tr>
            </table>
            <table border="0" width="35%">
                <tr>
                    <td>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                    <td><s:radio name="radio2" value="H" /> Hektar &nbsp;</td>
                    <td><s:radio name="radio2" value="M" /> Meter Persegi</td>
                    <td><s:radio name="radio2" value="MP" /> Meter Padu</td>
                </tr>
            </table>
            <br/>

            <div class="content" align="center">
                <tr>
                <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                    (Pelan kasar/Pelan tanah, untuk maksud pengenalan, dikeluarkan secara berasingan di dalam Borang P2e)
                </font>
                </tr> 
            </div>

            <p>
                <s:submit name="simpanPerihal" id="save" value="Simpan" class="btn" onclick="return validatePerihal();"/>&nbsp;
            </p>
            <!--<table border="0" width="10%" size="5"><tr><em>* Sila Pastikan Maklumat Di Atas Telah Disimpan</em></tr></table>-->
    </fieldset>
</div>

<br/>               
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            <div align="center"><font color="#001848"><b> SYARAT-SYARAT </b></font></div>
        </legend>
        <div class="content" align="left">
            <table border="0" width="85%">
                <tr>
                    <td width="100%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (1) Permit ini akan bermula pada <s:text name="tarikhMula" id="tarikhMula" class="datepicker" size="12" style="width:130px;" formatPattern="dd/MM/yyyy"/> 
                            dan tamat tempoh pada <s:text name="tarikhtamat" id="tarikhtamat" class="datepicker" size="12" style="width:130px;" formatPattern="dd/MM/yyyy"/>.
                        </font>
                    </td>
                </tr>
            </table>

            <table border="0" width="100%">           
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (2) Ruang udara atas tanah ${actionBean.syaratTanah} hendaklah tidak digunakan bagi apa-apa maksud selain dari mendirikan,
                            penyenggaraan dan pendudukan *struktur/ struktur-struktur yang diperihalkan di bawah ini yang kepadanya permit itu dikeluarkan :-
                        </font>
                    </td>                                                                                         
                </tr>
            </table>
            <br/>
            <table border="0" width="50%">  
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Jenis Struktur</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                    <td><s:text name="jenisStruktur" id="jenisStruktur" size="30" style="width:200px;" maxlength="30"/></td>
                </tr>
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Lokasi</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                    <td><s:text name="lokasi" id="lokasi" size="12" style="width:200px;" maxlength="30"/></td>
                </tr>
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Kawasan Ruang Udara Yang Terbabit (volume)</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                    <td>
                        <s:text name="luas" id="luas" size="12" style="width:200px;" maxlength="30" readonly="true"/>&nbsp;
                        <c:if test = "${actionBean.jenisLuas eq 'H'}">
                            Hektar
                        </c:if>
                        <c:if test = "${actionBean.jenisLuas eq 'M'}">
                            Meter Persegi
                        </c:if> 
                        <c:if test = "${actionBean.jenisLuas eq 'MP'}">
                            Meter Padu
                        </c:if>
                    </td>
                </tr>
            </table>

            <br/>
            <table border="0" width="35%"> 
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (3) Permit ini boleh dibatalkan di bawah seksyen 75G. 
                        </font>
                    </td>                                                                                         
                </tr>
            </table>
            <table border="0" width="85%"> 
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (4) Permit ini tidak boleh diserahak,kecuali dengan kelulusan Pihak Berkuasa Negeri terlebih dahulu. 
                        </font>
                    </td>                               
                </tr>
            </table>
            <table border="0" width="85%"> 
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            &nbsp;&nbsp;&nbsp;&nbsp;<s:textarea name="tambah" id="tambah"  rows="5" cols="100" />
                        </font>                       
                    </td>        

                </tr>
            </table>

            <br/>

            <br/>
            <p align="center">
                <s:submit name="simpanSyarat" id="save" value="Simpan" class="btn" onclick="return validateSyarat();"/>&nbsp;                  
            </p>
            <!--<div align="center"><table border="0" width="10%" size="5"><tr><em>* Sila Pastikan Maklumat Di Atas Telah Disimpan</em></tr></table></div>-->
        </div>
    </fieldset>
</div>
<br/>

<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            <div align="center"><font color="#001848"><b> MUAT NAIK PELAN </b></font></div>
        </legend>
        <div class="content" align="center" id="imej">
            <table border="1" align="center" class="tablecloth">   
                <c:if test="${actionBean.idMohonPgis != null}">
                    <display:table class="tablecloth" name="${actionBean.listPelanGIS}"
                                   id="line" style="width:20%"
                                   requestURI="${pageContext.request.contextPath}/permitEntry">                       
                        <display:column title="Papar">                           
                            <div align="center"><s:button name="paparPelan" id="save" value="Papar" class="btn" onclick="showReport('${actionBean.noFail}', 'DIS_PelanPermit_MLK.rdf')"/></div>
                        </display:column>
                        <display:column title="Hapus Pelan">                           
                            <div align="center"><s:submit name="deletePelan" id="save" value="Hapus" class="btn"/></div>
                        </display:column>
                    </display:table>
                </c:if>

                <c:if test="${actionBean.idMohonPgiswPermit != null}">
                    <display:table class="tablecloth" name="${actionBean.listPelanGISwNoPermit}"
                                   id="line" style="width:20%"
                                   requestURI="${pageContext.request.contextPath}/permitEntry">                       
                        <display:column title="Papar">                           
                            <div align="center"><s:button name="paparPelan" id="save" value="Papar" class="btn" onclick="showReport('${actionBean.noFail}', 'DIS_PelanPermit_MLK.rdf')"/></div>
                        </display:column>
                        <display:column title="Hapus Pelan">                           
                            <div align="center"><s:submit name="deletePelan" id="save" value="Hapus" class="btn"/></div>
                        </display:column>
                    </display:table>
                </c:if>

                <c:if test="${!(actionBean.idMohonPgis != null || actionBean.idMohonPgiswPermit != null)}">
                    <s:file name="fileToBeUpload" id="fileToBeUpload"/> &nbsp;&nbsp;&nbsp;
                    <s:submit name="muatnaik" value="Simpan" class="btn"/>                    
                </c:if>                                
            </table>
        </div>       
    </fieldset>
</div>
<br/>

<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            <div align="center"><font color="#001848"><b> JANA DOKUMEN</b></font></div>
        </legend>
        <div class="content" align="center">            
            <br/>  

            <p>
                <s:button name="jana" id="jana" value="Borang 4De" class="btn" onclick="showReport('${actionBean.noFail}', 'DISB4De_baru_MLK.rdf')"/>&nbsp;
                <s:button name="jana" id="jana" value="Borang P2e" class="btn" onclick="showReport('${actionBean.noFail}', 'DIS_BorangP2e_baru_MLK.rdf')"/>&nbsp;
            </p>
    </fieldset>
</div>
</stripes:form>


