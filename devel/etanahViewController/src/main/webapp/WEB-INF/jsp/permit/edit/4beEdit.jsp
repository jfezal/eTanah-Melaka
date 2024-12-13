<%-- 
    Document   : borang4be
    Created on : Sep 19, 2015, 10:19:47 AM
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
        window.open("${pageContext.request.contextPath}/digiSignPermit?carianBayaranPopup&noFail=" + id +"&jenisBorang=" + jb + "&noPermit=" + noPermit, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=350,scrollbars=yes");
    }
    
    function carianPemohon(id,jb,noPermit){          
        window.open("${pageContext.request.contextPath}/digiSignPermit?carianPihakPopup&noFail=" + id +"&jenisBorang=" + jb + "&noPermit=" + noPermit, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=350,scrollbars=yes");
    }
    
    function validateForm() {
        if ($("#daerah").val() == "") {
            alert('Sila Pilih Daerah !!!');
            return false;
        }else if ($("#maksud").val() == "") {
            alert('Sila Masukkan Maksud Pendudukan !!!');
            return false;
        }else if ($("#noResit").val() == "") {
            alert('Sila Masukkan No. Resit !!!');
            return false;
        }else if ($("#bayaran").val() == "") {
            alert('Sila Masukkan Bayaran !!!');
            return false;
        }else if ($("#nama").val() == "") {
            alert('Sila Masukkan Nama Pemegang Lesen !!!');
            return false;
        }else if ($("#alamat").val() == "") {
            alert('Sila Masukkan Alamat Pemegang Lesen !!!');
            return false;
        }else if ($("#noPengenalan").val() == "") {
            alert('Sila Masukkan No. Pengenalan Pemegang Lesen !!!');
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
    
    function validateJadual4be() {
        if ($("#tarikhMula").val() == "") {
            alert('Sila Pilih Tarikh Mula !!!');     
            return false;
        }else if ($("#tarikhtamat").val() == "") {
            alert('Sila Pilih Tarikh Tamat !!!');      
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
    
    function refreshImej(){
        var id = '${actionBean.noFail}';
        var url = '${pageContext.request.contextPath}/digiSignPermit?check&noFail='+id ;
        $.get(url,
        function(data){
            $("#imej").replaceWith($('#imej', $(data)));
        },'html');
    }
    
    function showReport(id,reportName){
        var noPermit = '${actionBean.noPermit}';
        var url = '${pageContext.request.contextPath}/reportGeneratorServlet?reportName='+reportName+'&report_p_id_mohon='+id+'&report_p_no_permit='+noPermit;  
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900px,height=700px,scrollbars=yes");
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
<stripes:form beanclass="etanah.view.stripes.permit.DSPermitActionBean" name="b4e">
    <s:messages/>
    <s:hidden name="noFail" id="noFail" />
    <s:hidden name="jenisBorang" id="jenisBorang" />
    <s:hidden name="noPermit" id="noPermit" />
    <s:hidden name="kodPermit" id="kodPermit" />

    <div class="subtitle">
        <fieldset class="aras1">
            <div class="content" align="left">                           
                <p>
                    <s:submit name="kembaliEdit" id="kembaliEdit" value="Kembali" class="btn"/>&nbsp;
                </p>
            </div>
            <br/>

            <legend>
                <td align="center"><font color="#001848">Kanun Tanah Negara</font></td>
            </legend>
            <br/>
            <div align="center"><font color="#001848"><b> Borang 4Be</b></font></div>
            <br/>
            <div align="center"><font color="#001848"><b> (Jadual Keenam Belas)</b></font></div>
            <br/>
            <div align="center"><font color="#001848"><b>LESEN PENDUDUKAN SEMENTARA (Borang Khas)</b></font></div>                              
            <br/>       

            <div class="content" align="center">
                <table border="0" width="50%">
                    <tr>
                        <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. Fail</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                        <td>
                            ${actionBean.noFail}                            
                        </td>
                    </tr>                    
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. L.P.S</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                        <td>${actionBean.kodPermit}  ${actionBean.noPermit}</td>                       
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
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Tujuan Pendudukan</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                        <td><s:textarea name="maksud" id="maksud"  rows="6" cols="10"  style="width:200px;"/></td>
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

            <div class="content" align="center" id="pihak">
                <table border="0" width="50%">
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Nama Pemegang Lesen</font></td>
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

            <div class="content" align="center">
                <tr>
                <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                    adalah dengan ini diberi kebenaran untuk menduduki tanah yang diperihalkan di bawah ini, bagi maksud,
                    dan pada fi, yang disebut di atas. Pendudukan hendaklah tertakluk kepada peruntukan
                    yang dijadualkan di bawah ini dan kepada mana - mana peruntukan lain yang ditetapkan
                    oleh Kaedah.
                </font>
                </tr> 
            </div>

            <div class="content" align="center">
                <table border="0" width="50%">                    
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Dikeluarkan Pada </font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                        <td><s:text name="tarikhPermit" id="tarikhPermit" class="datepicker" size="12" style="width:130px;" formatPattern="dd/MM/yyyy"/></td>
                    </tr> 
                </table>
            </div>

            <div class="content" align="center">
                <p>
                    <s:submit name="simpanPermitAsas" id="save" value="Simpan" class="btn" onclick="return validateForm();"/>&nbsp;
                </p>
            </div>

    </div>
</fieldset>
</div>

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
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Tanah Kerajaan/Tanah Rizab/Tanah Lombong</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                    <td>
                        <s:select name="tanah" id="tanah" style="width:200px;" >
                            <s:option value="">Pilih</s:option>
                            <s:options-collection collection="${list.kodPemilikanForRAndKAndPOnly}" label="nama" value="kod" />
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
                </tr>
            </table>
            <br/>

            <div class="content" align="center">
                <tr>
                <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                    (Pelan tanah, untuk tujuan pengenalan, dikeluarkan secara berasingan di dalam Borang L2e)
                </font>
                </tr> 
            </div>

            <p>
                <s:submit name="simpanPerihal" id="save" value="Simpan" class="btn" onclick="return validatePerihal();"/>&nbsp;
            </p>          
    </fieldset>
</div>
<br/>  

<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            <div align="center"><font color="#001848"><b> JADUAL </b></font></div>
        </legend>
        <div class="content" align="left">
            <table border="0" width="85%">
                <tr>
                    <td width="100%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (1) Lesen ini adalah bagi tempoh <s:text name="tarikhMula" id="tarikhMula" class="datepicker" size="12" style="width:130px;" formatPattern="dd/MM/yyyy"/> 
                            yang berakhir <s:text name="tarikhtamat" id="tarikhtamat" class="datepicker" size="12" style="width:130px;" formatPattern="dd/MM/yyyy"/>.
                        </font>
                    </td>
                </tr>
            </table>

            <table border="0" width="100%">           
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (2) Kuantiti maksimum bahan batuan yang boleh diekstrak adalah seperti berikut :
                        </font>
                    </td>                    
                </tr>
            </table>
            <table border="0" width="100%">           
                <tr>                 
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(i) <s:text name="kntt" id="kntt" size="12" style="width:50px;" maxlength="10" onkeyup="validateNumber(this,this.value);"/>
                            <s:select name="jenisUom" id="jenisUom" style="width:200px;" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodUOMByIsipadu}" label="nama" value="kod" />
                            </s:select>
                            setahun atau
                        </font>
                    </td>                    
                </tr>
            </table>
            <table border="0" width="100%">           
                <tr>                 
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(ii) <s:text name="kntt1" id="kntt" size="12" style="width:50px;" maxlength="10" onkeyup="validateNumber(this,this.value);"/>
                            <s:select name="jenisUom1" id="jenisUom" style="width:200px;" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodUOMByIsipadu}" label="nama" value="kod" />
                            </s:select>
                            kesemuanya dalam sepanjang seluruh tempoh.
                        </font>
                    </td>                    
                </tr>
            </table>
            <table border="0" width="100%">           
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (3) Sebagai tambahan kepada fi pendudukan (jika ada) yang disebut di atas suatu fi 
                            hendaklah kena dibayar pada kadar RM <s:text name="rm" id="rm" size="12" style="width:50px;" maxlength="7" onkeyup="validateNumber(this,this.value);" onblur="changeFormat2('rm')"/> bagi
                            <s:select name="bagi" id="bagi" style="width:200px;" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodUOMByIsipadu}" label="nama" value="kod" />
                            </s:select>
                            (kuantiti unit bahan batu itu).
                        </font>
                    </td>                   
                </tr>
            </table>   
            <table border="0" width="100%">           
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (4) Lesen ini "tidak boleh diserah/boleh diserah di bawah Kaedah <s:text name="kaedah" id="kaedah" size="17" style="width:200px;" maxlength="20" onblur="this.value=this.value.toUpperCase();"/>.
                        </font>
                    </td>                   
                </tr>
            </table> 
            <table border="0" width="100%">           
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (5) Lesen ini hendaklah tamat sekiranya berlaku kematian orang itu, atau pembubaran badan, yang 
                            buat masa itu berhak mendapat faedah darinya.
                        </font>
                    </td>                   
                </tr>
            </table> 
            <table border="0" width="100%">           
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (6) Tanah ini tidak boleh digunakan bagi apa - apa maksud selain dari pengekstrakan, pemprosesan 
                            atau pemindahan jenis bahan batuan yang dinyatakan di atas.
                        </font>
                    </td>                   
                </tr>
            </table> 
            <table border="0" width="85%"> 
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (7) Pemegang lesen boleh mendirikan dan menggunakan di atas tanah apa - apa sabak, bengkel, bilik stor
                            sebagaimana yang dinayatakan di bawah :-
                </tr>
            </table>
            <table border="0" width="85%"> 
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea name="tambah" id="tambah"  rows="5" cols="100" />
                        </font>                       
                    </td>        

                </tr>
            </table>
            <table border="0" width="100%">           
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (8) Lesen ini  bolehlah dibatalkan
                        </font>
                    </td>                    
                </tr>
                <table border="0" width="100%">           
                    <tr>                 
                        <td width="50%">
                            <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(i) dengan serta - merta, dan tanpa apa - apa bayaran pempasan, jika berlaku perlanggaran
                                mana - mana peruntukan yang kepadanya ia tertakluk.
                            </font>
                        </td>                    
                    </tr>
                </table>
                <table border="0" width="100%">           
                    <tr>                 
                        <td width="50%">
                            <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(ii) dengan bayaran pampasan (yang akan dipersetujui atau ditentukan mengikut peruntukan
                                seksyen 434 Kanun Tanah Negara) pada bila - bila masa sebelum tamat tempohnya.
                            </font>
                        </td>                    
                    </tr>
                </table>
                <table border="0" width="85%"> 
                    <tr>
                        <td width="50%">
                            <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea name="tambah1" id="tambah1"  rows="5" cols="100" />
                            </font>                       
                        </td>        

                    </tr>
                </table>
            </table>

            <br/>
            <p align="center">
                <s:submit name="simpanJadual4be" id="save" value="Simpan" class="btn" onclick="return validateJadual4be();"/>&nbsp;                  
            </p>
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
                                   requestURI="${pageContext.request.contextPath}/digiSignPermit">                       
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
                                   requestURI="${pageContext.request.contextPath}/digiSignPermit">                       
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

<br/>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            <div align="center"><font color="#001848"><b> JANA DOKUMEN</b></font></div>
        </legend>
        <div class="content" align="center">            
            <br/>  

            <p>
                <s:button name="jana" id="jana" value="Borang 4Be" class="btn" onclick="showReport('${actionBean.noFail}', 'DISB4Be_baru_MLK.rdf')"/>&nbsp;
                <s:button name="jana" id="jana" value="Borang L2e" class="btn" onclick="showReport('${actionBean.noFail}', 'DIS_BorangL2e_baru_MLK.rdf')"/>&nbsp;
            </p>
    </fieldset>
</div>
</stripes:form>


