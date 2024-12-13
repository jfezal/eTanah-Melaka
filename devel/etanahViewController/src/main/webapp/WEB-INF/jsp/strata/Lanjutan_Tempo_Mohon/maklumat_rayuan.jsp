<%--
    Document   : maklumat_rayuan
    Created on : Jul 22, 2010, 2:45:10 PM
    Author     : murali
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">

    function validate(){
        var tempohHari = document.getElementById("tempohHari2").value;

        if (tempohHari == "")
        {
            alert('Sila Masukkan Tarikh Kelulusan');
            document.getElementById("tempohHari2").focus();
            return false;
        }
        else{
            return true;                
        }
    }
    function validate2(){
        var tempohHari = document.getElementById("tempohHari2").value;

        if (tempohHari == "")
        {
            alert('Sila Masukkan Tarikh Kelulusan');
            document.getElementById("tempohHari2").focus();
            return false;
        }
        else{
            return true;                
        }
    }
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
    function deleteRow(idKandungan)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var url = '${pageContext.request.contextPath}/strata/maklumat_rayuan?deleteRow&idKandungan='+idKandungan;
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            $.get(url,
            function(data){
                $('#page_div').html(data);
                $.unblockUI();
            },'html');

        }
    }
    function validateForm(){
        var kandungan = document.getElementById("kandungan21").value;
        if(kandungan ==""){
            alert("Sila masukkan maklumat dan simpan pada ruangan 2.1 sebelum tambah maklumat berikutnya.");
            return false
        }
        return true;
    }

    function addRow(index)
    {
        if(confirm('Adakah anda pasti untuk menambah data ini?')) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var url = '${pageContext.request.contextPath}/strata/maklumat_rayuan?tambahRow&index='+index;

            $.get(url,
            function(data){
                $('#page_div').html(data);
                $.unblockUI();
            },'html');

        }
    }
    function menyimpan(index,i)
    {var kand;
        if(index==2){
            kandungan = document.getElementById("kandungan2"+i).value;
        }
        if(index==3){
            kandungan = document.getElementById("kandungan3"+i).value;
        }
        if(index==4){
            kandungan = document.getElementById("kandungan4"+i).value;
        }

        if(confirm('Adakah anda pasti untuk simpan data ini?')) {
            var url = '${pageContext.request.contextPath}/strata/maklumat_rayuan?simpan&index='+index+'&kandungan='+kandungan;
                +index;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

        }
    }

    function dateValidation(id, value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate < today){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#'+id).val("Sili Pilih Semula");
        }
    }
    function dpicker(){
        $("#tarikhSiap").datepicker({dateFormat: 'dd/mm/yy'});
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.strata.MaklumatRayuanActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${!headerRayuan}">
                <legend>Masalah/Isu Yang Timbul</legend>
            </c:if>
            <c:if test="${headerRayuan}">
                <legend>Maklumat Rayuan</legend>
            </c:if>
            <p align="center">
            <table width="70%" border="1" class="tablecloth" align="center" >
                <c:if test="${displayTarikh}">
                    <c:if test="${edit}">
                        <tr>
                            <td colspan="3">
                                <center> <b> Tarikh Lanjutan Yang Dipohon :</b> &nbsp;<s:text class="datepicker" formatPattern="dd/MM/yyyy" name="tarikhPohon" size="30" id="tempohHari2"  onmouseover="dpicker()" onclick="dpicker();" onchange="dateValidation(this.id, this.value)"/></center>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr>
                            <td colspan="2">
                                <center> <b> Tarikh Lanjutan Yang Dipohon :</b> &nbsp;<s:text readonly="true" formatPattern="dd/MM/yyyy" name="tarikhPohon" size="30" id="tempohHari"  onmouseover="dpicker()" onclick="dpicker();" onchange="dateValidation(this.id, this.value)"/></center>
                            </td>
                        </tr>
                    </c:if>
                </c:if>
                <c:if test="${fn:length(actionBean.listMohonKertasKand) == 0 or actionBean.listMohonKertasKand eq null}">
                    <c:if test="${edit}">
                    <tr>
                        <td width="23" valign="top"><c:out value="2.1"/></td>
                        <td><s:textarea  id="kandungan21" name="kandungan" cols="90"  rows="5" class="normal_text"/></td>
                        <td></td>
                    </tr>
                    </c:if>
                    <c:if test="${!edit}">
                    <tr>
                        <td width="23" valign="top"><c:out value="2.1"/></td>
                        <td><s:textarea readonly="true" id="kandungan21" name="kandungan" cols="90"  rows="5" class="normal_text"/></td>
                        <td></td>
                    </tr>
                    </c:if>

                </c:if>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.listMohonKertasKand}" var="line">


                    <tr>
                        <td width="23" valign="top"><c:out value="2.${i}"/></td>
                        <td><s:hidden name="listMohonKertasKand[${i-1}].bil"/> <s:hidden name="listMohonKertasKand[${i-1}].subtajuk"/><s:textarea  id="kandungan2${i}" name="listMohonKertasKand[${i-1}].kandungan" readonly="${actionBean.readOnly}" cols="90"  rows="5" class="normal_text"/></td>
                        <c:if test="${edit}">
                            <td valign="top"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button> </td>
                        </c:if>
                    </tr><c:set var="i" value="${i+1}" />
                </c:forEach>
                <c:if test="${edit}">
                    <tr>
                        <td width="23" valign="top"></td>
                        <td  align="left">
                            <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'PNB' || actionBean.permohonan.kodUrusan.kod eq 'RMH1A'
                                          || actionBean.permohonan.kodUrusan.kod eq 'RMHS1' || actionBean.permohonan.kodUrusan.kod eq 'RMHS6'
                                          || actionBean.permohonan.kodUrusan.kod eq 'RMHS5' || actionBean.permohonan.kodUrusan.kod eq 'RMHS7')}">  
                                <c:if test="${kaliPertama}">
                                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('2')"></s:button> <s:button name="simpanKaliPertama" id="save1" value="Simpan" class="btn" onclick="if (validate()){doSubmit(this.form,this.name,'page_div');}"></s:button>
                                </c:if>
                                <c:if test="${!kaliPertama}">
                                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('2')"></s:button> <s:button name="simpan" id="save1" value="Simpan" class="btn" onclick="if (validate2()){doSubmit(this.form,this.name,'page_div');}"></s:button>
                                </c:if> 
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PNB' || actionBean.permohonan.kodUrusan.kod eq 'RMH1A' 
                                          || actionBean.permohonan.kodUrusan.kod eq 'RMHS1' || actionBean.permohonan.kodUrusan.kod eq 'RMHS6'
                                          || actionBean.permohonan.kodUrusan.kod eq 'RMHS5' || actionBean.permohonan.kodUrusan.kod eq 'RMHS7'}"> 
                                <c:if test="${kaliPertama}">
                                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('2')"></s:button> <s:button name="simpanKaliPertama" id="save1" value="Simpan" class="btn" onclick="doSubmit(this.form,this.name,'page_div')"></s:button>
                                </c:if>
                                <c:if test="${!kaliPertama}">
                                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('2')"></s:button> <s:button name="simpan" id="save1" value="Simpan" class="btn" onclick="doSubmit(this.form,this.name,'page_div')"></s:button>
                                </c:if> 
                            </c:if>
                        <td></td>
                    </tr>
                </c:if>
            </table>
        </fieldset>
    </div>
</s:form>

