<%-- 
    Document   : kemasukanSifusPopup
    Created on : Aug 18, 2016, 4:39:27 PM
    Author     : siti.mudmainnah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">

    $(document).ready(function () {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function edit1(x)
    {
        var sts = $('#status').val();
        var url = '${pageContext.request.contextPath}/strata/KemasukkanSifus?save&idProjek=' + x + '&status=' + sts;
        $.get(url,
                function (data) {
                    $('#page_div').html(data);
                }, 'html');
    }

    function validateNumber(elmnt, content) {
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric(strString)
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

</script>


<s:errors/>
<s:messages/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form beanclass="etanah.view.strata.utiliti.KemasukanSifus">
    <div id="page_div">
        <fieldset class="aras1">
            <legend>Kemasukan</legend>

            <c:if test="${edit}">
                <br />
                <br />
                <label>Id Hakmilik :</label>${actionBean.idHakmilik}
                <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                <s:hidden name="sifus.idProjek" value="${actionBean.sifus.idProjek}"/>
                </p>
                <p>
                    <label>Nama Pemohon :</label><s:text name="sifus.namaPemaju" onkeyup="this.value = this.value.toUpperCase();" size="60"/>
                </p>
                <p>
                    <label>Nama Pemilik :</label><s:text name="sifus.pemilik" onkeyup="this.value = this.value.toUpperCase();" size="60"/>
                </p>
                <p>
                    <label>Nama Skim : </label><s:text name="sifus.jenisProjek" onkeyup="this.value = this.value.toUpperCase();" size="60"/>
                </p>
                <p>
                    <label>No. Rujukan Pelan Bangunan : </label><s:text name="sifus.noRujukanProjek" onkeyup="this.value = this.value.toUpperCase();" size="60"/>
                </p>
                <p>
                    <label>Formula Unit Syer : </label><s:text name="sifus.formula" size="60" onkeyup="this.value = this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Tarikh Kelulusan Sijil : </label>
                <s:text name="sifus.tarikhLulus" size="60" class="datepicker" formatPattern="dd/MM/yyyy"/>
                </p>
                <p>
                    <label>Tarikh Kelulusan Pelan Bangunan : </label>
                <s:text name="sifus.tarikhLulusBngn" size="60" class="datepicker" formatPattern="dd/MM/yyyy"/>
                </p>
                <p>
                    <label>No. Siri : </label><s:text name="sifus.maksimumUnit" size="30" onkeyup="validateNumber(this, this.value);"/>
                </p>
                <p>
                    <label>No. Rujukan Fail : </label><s:text name="sifus.noRujFail" size="60" onkeyup="this.value = this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Bilangan Petak : </label><s:text name="sifus.jumlahSemuaUnit" size="30" onkeyup="validateNumber(this, this.value);"/>
                </p>
                <p>
                    <label>Bilangan Petak Aksesori : </label><s:text name="sifus.bilAksr" size="30" onkeyup="validateNumber(this, this.value);"/>
                </p>
                <p>
                    <label>Jumlah Unit Syer : </label><s:text name="sifus.unitDiJual" size="30" onkeyup="validateNumber(this, this.value);"/>
                </p>
                <p>
                    <label>Jenis Kengunaan Bangunan :</label> 
                <s:select name="gunaBngn" value="${actionBean.sifus.gunaBngn.kod}">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodKegunaanBangunan}" label="nama" value="kod"/>
                </s:select>
                </p>
                <p>
                    <label>Jenis Kengunaan Bangunan (Lain - Lain):</label> 
                <s:text name="sifus.kegunaanLain" size="60" onkeyup="this.value = this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Status :</label> 
                <s:select name="status" id="status" value = "${actionBean.sifus.aktif}">
                    <s:option value="">Pilih ...</s:option>
                    <s:option value="Y">Aktif</s:option>
                    <s:option value="T">Tidak Aktif</s:option>
                    <s:option value="M">Mohon</s:option>
                    <s:option value="B">Batal</s:option>
                </s:select>
                </p>
                <p align="center">
                <!--<s:button class="longbtn" name="save" value="Simpan" id="edit" onclick="edit1('${actionBean.sifus.idProjek}');"/>-->
                <s:submit class="longbtn" name="save" value="Simpan" id="edit"/>
                <s:button class="longbtn" name="" value="Tutup" onclick="self.close()"/>
                </p>
            </c:if>
            <c:if test="${add}">
                <div align="left">

                    <fieldset>

                        <p>
                            <label>Id Hakmilik :</label>${actionBean.idHakmilik}
                        <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                        </p>
                        <p>
                            <label>Nama Pemohon :</label><s:text name="sifus.namaPemaju" onkeyup="this.value = this.value.toUpperCase();" size="60"/>
                        </p>
                        <p>
                            <label>Nama Pemilik :</label><s:text name="sifus.pemilik" onkeyup="this.value = this.value.toUpperCase();" size="60"/>
                        </p>
                        <p>
                            <label>Nama Skim : </label><s:text name="sifus.jenisProjek" onkeyup="this.value = this.value.toUpperCase();" size="60"/>
                        </p>
                        <p>
                            <label>No. Rujukan Pelan Bangunan : </label><s:text name="sifus.noRujukanProjek" onkeyup="this.value = this.value.toUpperCase();" size="60"/>
                        </p>
                        <p>
                            <label>Formula Unit Syer : </label><s:text name="sifus.formula" size="60" onkeyup="this.value = this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Tarikh Kelulusan Sijil : </label>
                        <s:text name="sifus.tarikhLulus" size="60" class="datepicker" formatPattern="dd/MM/yyyy"/>
                        </p>
                        <p>
                            <label>Tarikh Kelulusan Pelan Bangunan : </label>
                        <s:text name="sifus.tarikhLulusBngn" size="60" class="datepicker" formatPattern="dd/MM/yyyy"/>
                        </p>
                        <p>
                            <label>No. Siri : </label><s:text name="sifus.maksimumUnit" size="30" onkeyup="validateNumber(this, this.value);"/>
                        </p>
                        <p>
                            <label>No. Rujukan Fail : </label><s:text name="sifus.noRujFail" size="60" onkeyup="this.value = this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Bilangan Petak : </label><s:text name="sifus.jumlahSemuaUnit" size="30" onkeyup="validateNumber(this, this.value);"/>
                        </p>
                        <p>
                            <label>Bilangan Petak Aksesori : </label><s:text name="sifus.bilAksr" size="30" onkeyup="validateNumber(this, this.value);"/>
                        </p>
                        <p>
                            <label>Jumlah Unit Syer : </label><s:text name="sifus.unitDiJual" size="30" onkeyup="validateNumber(this, this.value);"/>
                        </p>
                        <p>
                            <label>Jenis Kengunaan Bangunan :</label> 
                        <s:select name="gunaBngn">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKegunaanBangunan}" label="nama" value="kod"/>
                        </s:select>
                        </p>
                        <p>
                            <label>Jenis Kengunaan Bangunan (Lain - Lain):</label> 
                        <s:text name="sifus.kegunaanLain" size="60" onkeyup="this.value = this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Status :</label> 
                        <s:select name="sifus.aktif">
                            <s:option value="">Pilih ...</s:option>
                            <s:option value="Y">Aktif</s:option>
                            <s:option value="T">Tidak Aktif</s:option>
                        </s:select>
                        </p>
                        <p align="center">
                        <s:submit class="longbtn" name="saveBru" value="Simpan" id="edit"/>
                        <s:button class="longbtn" name="" value="Tutup" onclick="self.close()"/>
                        </p>


                    </fieldset>
                </div>

            </c:if>
        </fieldset>
    </div>
</s:form>
