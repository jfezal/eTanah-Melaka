<%--
    Document   : testing1
    Created on : Dec 06, 2012, 10:42:01 AM
    Author     : Murali
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<script type="text/javascript">
    var date = new Date();
    $(document).ready(function () {
        var now = new Date();
        var year = now.getFullYear();
        $('#tahun').val(year);
        $(".datepicker").datepicker({dateFormat: 'd/m/yy'});
        $("#pengurusanTarikhSijil2").val(date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear());
    <%--$("#pengurusanTarikhRujukan").val(date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getFullYear());--%>
    });

    function validation() {
        if ($("#pengurusanTarikhSijil").val() == "") {
            $("#pengurusanTarikhSijil").val(date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear());
        }
        if ($("#pengurusanNama").val() == "") {
            alert('Sila masukkan " Nama Perbadannan Pengurusan " terlebih dahulu.');
            $("#pengurusanNama").focus();
            return false;
        }
        if ($("#pengurusanAlamat1").val() == "") {
            alert('Sila masukkan " Alamat " terlebih dahulu.');
            $("#pengurusanAlamat1").focus();
            return false;
        }
        if ($("#pengurusanPoskod").val() == "") {
            alert('Sila masukkan " Poskod " terlebih dahulu.');
            $("#pengurusanPoskod").focus();
            return false;
        }
        if ($("#pengurusanKodNegeri").val() == "SILA PILIH") {
            alert('Sila pilih " Negeri " terlebih dahulu.');
            $("#pengurusanKodNegeri").focus();
            return false;
        }
        if ($("#pengurusanTarikhSijil").val() == "") {
            alert('Sila masukkan " Tarikh Daftar Strata " terlebih dahulu.');
            $("#pengurusanTarikhSijil").focus();
            return false;
        }
        if ($("#pengurusanNoRujukan").val() == "") {
            alert('Sila masukkan " No. Fail " terlebih dahulu.');
            $("#pengurusanNoRujukan").focus();
            return false;
        }
        if ($("#pengurusanTarikhRujukan").val() == "") {
            alert('Sila masukkan " Tarikh Sijil Dikeluarkan " terlebih dahulu.');
            $("#pengurusanTarikhRujukan").focus();
            return false;
        }
        return true;
    }

    function resetForm() {
        var url = "${pageContext.request.contextPath}/strata/sijil_perbadanan?resetForm";
        $.post(url,
                function (data) {
                    $('#page_div').html(data);
                }, 'html');
    }

    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
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
    function simpan(event, f) {

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function (data) {
                    $('#page_div').html(data);

                }, 'html');
    }

    function dateValidation(id, value) {
        var vsplit = value.split('/');
        var fulldate = vsplit[1] + '/' + vsplit[0] + '/' + vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today) {
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#' + id).val("Sila Pilih Semula");
        }
        var startDate = new Date($("#pengurusanTarikhSijil").val());
        var endDate = new Date($("#pengurusanTarikhRujukan").val());

        if (startDate > endDate) {
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#' + id).val("Sila Pilih Semula");
        }
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.strata.SijilPerbadananActionBean" name="form1">

    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Draf Sijil Perbadanan Pengurusan</legend>
            <br>

            <p>
                <label>Nama Perbadanan Pengurusan : </label><s:text name="pengurusanNama" size="100"  id="pengurusanNama" onkeyup="this.value=this.value.toUpperCase();" readonly="true"/>
            </p>

            <p>
                <label>No Syarikat : </label><s:text name="pengurusanNoPengenalan" id="pengurusanNoPengenalan" onkeyup="this.value=this.value.toUpperCase();" readonly="true"/>
            </p>

            <p>
                <label>Alamat :</label>
                <s:text name="pengurusanAlamat1" size="100" maxlength="60" id="pengurusanAlamat1" onkeyup="this.value=this.value.toUpperCase();" readonly="true"/>
            </p>

            <p>
                <label> &nbsp;</label>
                <s:text name="pengurusanAlamat2" size="80" maxlength="60" id="pengurusanAlamat2" onkeyup="this.value=this.value.toUpperCase();" />
            </p>

            <p>
                <label> &nbsp;</label>
                <s:text name="pengurusanAlamat3" size="80" maxlength="60" id="pengurusanAlamat3" onkeyup="this.value=this.value.toUpperCase();" />
            </p>

            <p>
                <label> &nbsp;</label>
                <s:text name="pengurusanAlamat4" size="80" maxlength="60" id="pengurusanAlamat4" onkeyup="this.value=this.value.toUpperCase();" />
            <p>
                <label>Poskod :</label>
                <s:text name="pengurusanPoskod" maxlength="5" id="pengurusanPoskod" onkeyup="validateNumber(this,this.value);" />
            </p>

            <p>
                <label>Negeri :</label>
                <s:hidden name="pengurusanKodNegeri" id="pkodNegeri"  value="${actionBean.pengurusanKodNegeri}"/>
                <s:select name="pengurusanKodNegeri" id="pkodNegeri" value="${actionBean.pengurusanKodNegeri}">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            <c:if test="${actionBean.pengurusanTarikhSijil != null}">
                <p>
                    <label>Tarikh Daftar Strata  :</label><s:text name="pengurusanTarikhSijil" id="pengurusanTarikhSijil" readonly="true" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)" /><em>*</em>

                </p>
            </c:if>
            <c:if test="${actionBean.pengurusanTarikhSijil == null}">
                <p>
                    <label>Tarikh Daftar Strata  :</label><s:text name="pengurusanTarikhSijil" class="datepicker" id="pengurusanTarikhSijil2" readonly="true" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)" /><em>*</em>

                </p>
            </c:if>
            <p>
                <label>No Fail:</label><s:text name="pengurusanNoRujukan" id="pengurusanNoRujukan" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/><em>*</em>
            </p>

            <%-- <c:if test="${actionBean.pengurusanTarikhRujukan == null}">
                 <p>
                     <label>Tarikh Sijil Dikeluarkan :</label><s:text name="pengurusanTarikhRujukan" class="datepicker" id="pengurusanTarikhRujukan" value="${actionBean.pengurusanTarikhRujukan}" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/><em>*</em>
                 </p>
             </c:if>
             <c:if test="${actionBean.pengurusanTarikhRujukan != null}">
                 <p>
                     <label>Tarikh Sijil Dikeluarkan :</label><s:text name="pengurusanTarikhRujukan" class="datepicker" id="pengurusanTarikhRujukan1" value="${actionBean.pengurusanTarikhRujukan}" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/><em>*</em>
                 </p>
             </c:if>--%>
            <s:hidden name="tahun" id="tahun" />

            <p>
                <label>&nbsp;</label>

                <s:button name="simpandraf" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                <!--
                <s:button name="updateDraf" value="Kemaskini" class="btn" onclick="simpan(this.name, this.form);"/>
                -->
                <!--s:button class="btn" value="Set Semula" name="new" id="new" onclick="resetForm()"/-->&nbsp;
            </p>
            <p>
        </fieldset>
    </div>
</s:form>