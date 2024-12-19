<%--
	Document: kemasukan_maklumat_akaun
	Author: Mohd Hairudin Mansur
	Version: 1.0 16 December 2009
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<script type="text/javascript">
    function addNew(){
        window.open("${pageContext.request.contextPath}/hasil/kemasukan_maklumat_akaun?showPemegangAkaunPopup", "eTanah",
        "status=0,toolbar=0,scrollbars=1,location=0,menubar=0,width=910,height=800");
    }

    function updateTotal (inputTxt){
        var a = document.getElementById('baki');
        if ((isNaN(a.value))||((a.value) =="")){
            alert("Nombor tidak sah");
            inputTxt.value = RemoveNonNumeric(a);
            return;
        }
        inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
    }

    function RemoveNonNumeric( strString ){
        var strValidCharacters = "1234567890.";
        var strReturn = "0.00";
        var strBuffer = "0";
        var intIndex = 0.00;
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

    function checking(fail){
        $.get("${pageContext.request.contextPath}/hasil/kemasukan_maklumat_akaun?checkingNoFile&noRuj=" + fail,
        function(data){
            if(data =='1'){
                alert("No. Fail / Rujukan " + fail + " telah wujud. Sila masukkan semula No. Fail / Rujukan");
                $("#catatan").val("");
            }
        });
    }

    function validate(){
        var kodAkaun = document.getElementById('kodAkaun');
        var idHakmilik = document.getElementById('idHakmilik');
        var jenisPengenalan = document.getElementById('jenisPengenalan');
        var noPengenalan = document.getElementById('noPengenalan');
        var nama = document.getElementById('nama');
        if(kodAkaun.value == "0"){
            alert("Sila pilih Jenis Akaun Amanah");
            return false;
        }else if(idHakmilik.value == ""){
            alert("Sila masukkan ID Hakmilik yang terlibat");
            return false;
        }else if(jenisPengenalan.value == ""){
            alert("Sila pilih Jenis Pengenalan");
            return false;
        }else if(noPengenalan.value == ""){
            alert("Sila masukkan Nombor Pengenalan");
            return false;
        }else if(nama.value == ""){
            alert("Sila masukkan nama Pemegang");
            return false;
        }
    }
</script>
<div align="center"><table style="width:99.2%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Penyelenggaraan Akaun Amanah</font>
            </div>
        </td>
    </tr>
</table></div>
<s:messages/>
<s:errors/>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>Kemasukan Maklumat Akaun</legend>
        <div class="instr-fieldset">
            <font color="red">Perhatian :</font> Medan bertanda<em>*</em>adalah mandatori.
        </div>
        <s:form beanclass="etanah.view.stripes.hasil.KemasukanMaklumatAkaunActionBean" id="maklumat_akaun">

            <p><label><u>Maklumat Akaun</u></label></p><br/>
            <p>
                <label><em>*</em>Jenis Akaun :</label>
                <s:select name="akaun.kodAkaun.kod" id="kodAkaun">
                    <option value="0">Pilih....</option>
                    <c:forEach items="${listUtil.senaraiKodAkaunJenisAmanah}" var="j" >
                        <option value="${j.kod}" >${j.kod} - ${j.nama}</option>
                    </c:forEach>
                </s:select>
            </p>
            <p>
                <label><%--<em>*</em>--%>ID Hakmilik :</label>
                <s:text name="akaun.hakmilik.idHakmilik" id="idHakmilik" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>ID Permohonan :</label>
                <s:text name="akaun.permohonan.idPermohonan" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <%--<p>
                <label>Kod Jabatan (SPEKS) :</label>
                <s:text name="akaun.kodSpeksJabatan" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Kod PTJ :</label>
                <s:text name="akaun.kodSpeksPTJ" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>--%>
            <p>
                <label><em>*</em>No. Fail/Rujukan :</label>
                <s:text name="akaun.catatan" id="catatan" onblur="checking(this.value)"/>
            </p>
            <p>
                <label><em>*</em>Jumlah Bayaran (RM) :</label>
                <s:text name="akaun.baki" id="baki" onblur="updateTotal(this);"/>
            </p>

            <br/>
            <p><label><u>Maklumat Pemegang</u></label></p><br/>
            <p>
                <s:hidden name="akaun.pemegang.idPihak" id="idPemegang" />
                <label><%--<em>*</em>--%>Jenis Pengenalan :</label>
                <s:hidden name="akaun.pemegang.jenisPengenalan.kod" id="kodPengenalan" />
                <s:text name="akaun.pemegang.jenisPengenalan.nama" readonly="true" id="jenisPengenalan" />
                <s:button name="new" value="Cari Pemegang" id="new" onclick="addNew();" class="btn"/>
            </p>
            <p>
                <label><%--<em>*</em>--%>No. Pengenalan :</label>
                <s:text name="akaun.pemegang.noPengenalan" readonly="true" id="noPengenalan" />
            </p>
            <p>
                <label><%--<em>*</em>--%>Nama :</label>
                <s:text name="akaun.pemegang.nama" readonly="true" id="nama"/>
            </p>
            <br/>
            <div align="center"><table style="width:99.2%" bgcolor="green">
                <tr>
                    <td  align="right">
                        <%--<s:submit name="simpanMaklumatAkaun" value="Simpan" class="btn" onclick="return validate()"/>--%>
                        <s:submit name="simpanMaklumatAkaun" value="Simpan" class="btn"/>
                        <s:button name="reset" value="Isi Semula" class="btn" onclick="clearText('maklumat_akaun');"/>
                        <s:submit name="kembali" value="Kembali" class="btn"/>
                    </td>
                </tr>
            </table>

        </s:form>

    </fieldset>
</div>