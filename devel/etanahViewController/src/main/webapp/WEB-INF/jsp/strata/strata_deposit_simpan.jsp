<%-- 
    Document   : pengambilan_deposit_papar
    Created on : Dec 18, 2012, 2:11:49 PM
    Author     : User
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<%-- carian hakmilik start--%>

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<%-- carian hakmilik end--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
    
    function validateNumber2(elmnt,content) {
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
    
    function resetForm(){
        var url = "${pageContext.request.contextPath}/strata/depositProvisionalBlok?resetForm";
        $.post(url,
        function(data){
            $('#page_div').html(data);
        },'html');

    }
    
    function validation(f,b,c,d){

        if($("#caraBayaran").val() == ""){
            alert('Sila pilih " Cara Pembayaran " terlebih dahulu.');
            $("#caraBayaran").focus();
            return false;
        }
        else if($("#jenisBank").val() == ""){
            alert('Sila pilih " Jenis Bank " terlebih dahulu.');
            $("#jenisBank").focus();
            return false;
        }
        else if($("#noAkaun").val() == ""){
            alert('Sila masukkan " No Akaun " terlebih dahulu.');
            $("#noAkaun").focus();
            return false;
        } 
        else if($("#catatan").val() == ""){
            alert('Sila masukkan " Catatan " terlebih dahulu.');
            $("#catatan").focus();
            return false;
        }
        else{
            //            var url = '$pageContext.request.contextPath}/strata/depositProvisionalBlok?simpanPendeposit';
            //            $.post(url,$("#form").serialize(),
            //            function(data){
            //                $('#page_div').html(data);
            //            },'html');
            return true;
        }
    }
    
    $(document).ready(function() {
        //            maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    }); //END OF READY FUNCTION

    
   
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.strata.DepositProvisionalBlokActionBean" name="form" id="form">
    <s:errors/>
    <s:messages/>

    <fieldset class="aras1">
        <%--<c:if test="${edit}">--%>
        <legend>Maklumat Pendeposit</legend>
        <div class="subtitle" id="main">
            <%--class="content"--%> 
            <div align="center">
                <s:hidden name="idPihak" id="idPihak" value="pemohon.pihak.idPihak"/>
                <%--${actionBean.pihak.idPihak}--%>
                <table align="left">
                    <p></p>
                    <c:if test="${actionBean.pemohon.pihak.jenisPengenalan.nama ne null}">
                        <tr>
                            <td>
                                <label>Jenis Pengenalan :</label>
                            </td>
                            <td>
                                <s:text name="pemohon.pihak.jenisPengenalan.nama" id="nama" size="20" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>                          
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${actionBean.pemohon.pihak.noPengenalan ne null}">
                        <tr>
                            <td><label>No Pengenalan :</label></td>
                            <td>
                                <s:text name="pemohon.pihak.noPengenalan" id="noPengenalan" size="20" onkeyup="dodacheck(this.value);" onblur="doUpperCase(this.id)" disabled="true"/>
                                <font title="No KP Baru: 780104069871, No KP Lama: A2977884, No Syarikat: 115793-P, No Pertubuhan: 432483-U"><%--<em>[780104069871]</em>--%></font>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td><%--<font color="red" size="4">*</font>--%><label>Nama :</label></td>
                        <td>
                            <s:text name="pemohon.pihak.nama"  id="nama" size="40" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td><%--<font color="red" size="4">*</font>--%><label>Alamat :</label></td>
                        <td>
                            <s:text name="pemohon.pihak.alamat1" id="alamat1" size="40" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>
                            <s:text name="pemohon.pihak.alamat2" id="alamat2" size="40" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>
                            <s:text name="pemohon.pihak.alamat3" id="alamat3" size="40" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>
                            <s:text name="pemohon.pihak.alamat4" id="alamat4" size="40" onkeyup="this.value=this.value.toUpperCase();" disabled="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td><%--<font color="red" size="4">*</font>--%><label>Poskod :</label></td>
                        <td>
                            <s:text name="pemohon.pihak.poskod"  size="10" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);" disabled="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td><%--<font color="red" size="4">*</font>--%><label>Negeri :</label></td>
                        <td>
                            <s:text name="pemohon.pihak.negeri.nama"  size="40" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);" disabled="true"/>
                            <%--<s:select name="pihak.negeri.kod" id="negeri">
                                <s:option value="0">Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                            </s:select>--%>
                        </td>
                    </tr>
                    <tr>
                        <td><%--<font color="red" size="4">*</font>--%><label>No Telefon :</label></td>
                        <td>
                            <s:text name="pemohon.pihak.noTelefon1" id="noTelefon1" size="" onkeyup="validateNumber(this,this.value);" disabled="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td><%--<font color="red" size="4">*</font>--%><label>Email :</label></td>
                        <td>
                            <s:text name="pemohon.pihak.email" id="emel" size="40" class="normal_text" disabled="true"/> <%--<em>[alamat_emel@gmail.com]</em>--%>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </fieldset>
    <br/><br/>
    <fieldset class="aras1">

        <legend>Maklumat Bank</legend>
        <div class="instr-fieldset">
            <font color="red">PERINGATAN:Ruangan bertanda (</font> <font color="red">*</font> <font color="red">) adalah wajib diisi.</font>
        </div>
        <table  align="left">
            <tr>
                <td><label>Amaun (RM) :</label></td>
                <td>
                    <s:text name="amaunProv1" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" readonly="true"/>
                </td>
            </tr>
            <tr>
                <td><label><font color="red">*</font>Cara Bayar :</label></td>
                <td>
                    <s:select name="caraBayaran" id="caraBayaran">
                        <s:option value="" selected="selected">Sila Pilih</s:option>
                        <s:option value="CT">Cek Tempatan</s:option>
                        <s:option value="EF">Wang Dalam Pindahan</s:option>
                        <s:option value="DB">Deraf Bank</s:option>
                        <s:option value="T">Tunai</s:option>
                    </s:select>
                </td>
            </tr>
            <tr>
                <td><label><font color="red">*</font>Jenis Bank :</label></td>
                <td>
                    <s:select name="jenisBank" id="jenisBank">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodBank}" label="nama" value="kod"/>
                    </s:select>                   
                </td>
            </tr>
            <tr>
                <td><label><font color="red">*</font>No Akaun Bank :</label></td>
                <td>
                    <%--<s:text name="noAkaun" id="noAkaun"  size="30" onkeyup="validateNumber2(this,this.value);"/>--%>
                    <s:text name="noAkaun" id="noAkaun"  size="30"/>
                </td>
            </tr>

            <tr>
                <td valign ="top"><label><font color="red">*</font>Catatan :</label></td>
                <td>
                    <s:textarea name="catatan" id="catatan" cols="60" rows="5" class="normal_text" />
                </td>
            </tr>
            <tr>
                <td align="center" colspan="2">&nbsp;</td>
            </tr>
            <tr>               
                <td align="center">&nbsp;</td>
                <td align="left">
                    <s:button name="simpanPendeposit" id="save" value="Simpan" class="btn" onclick="if(validation()==true){doSubmit(this.form, this.name, 'page_div',idPihak)}"/>&nbsp;&nbsp;<s:button class="btn" value="Isi Semula" name="new" id="new" onclick="resetForm()"/>&nbsp;

                </td>
            </tr>
        </table>


        <br/>

        <%--</c:if>--%>
    </fieldset>
</s:form>

</body>
