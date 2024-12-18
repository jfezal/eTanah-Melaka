<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%-- <script type="text/javascript"
         src="<%= request.getContextPath()%>/scripts/jquery-1.3.2.min.js"></script>--%>

<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery.bestupper.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/fraction.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">

    $(document).ready(function () {
        //filterKodSeksyen();
        //filterKodGunaTanah();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function kiraCukaiKelompok(id, i) {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        var kodUOM = $("#kodUOM" + i).val();
        var luas = $("#luas" + i).val();
        $.post('${pageContext.request.contextPath}/strata/maklumat_hakmilik_permohonanNew?kiraCukaiKelompok&idHakmilik=' + id + '&kodUOM=' + kodUOM + '&luas=' + luas,
                function (data) {
                    if (data != '') {

                        $('#cukai' + i).val(convert(data, 'cukai' + i));
                        $.unblockUI();
                    }
                }, 'html');
    }

    function validate() {
        var sValue = document.getElementById("noPelan1").value;
        var pattern2 = /^\d{5}([\-]\d{4})?$/;
        var pattern = /^[0-9@!#\$\^%&*()+=\-\[\]\\\';,\.\/\{\}\|\":<>\? ]+$/;
        if (pattern.test(sValue)) {
            alert("Your Input is valid : " + sValue);
            return true;
        }
        alert("Input except Number & Special Characters only !");
        return false;

    }

    function validation2() {
        var noPelan = document.getElementById("noPelan1").value;
        var noPu = document.getElementById("noPu").value;

        if (noPelan == "") {
            alert("Sila masukkan No PA(B)");
            return false;
        }
        if (noPu == "") {
            alert("Sila masukkan No. Helaian");
            return false;
        }

        return true;
    }

    function removeNonNumeric(strString)
    {
        var strValidCharacters = "1234567890-";
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

    function toggleSelectDocument(selectInput, i) {
        if (selectInput.checked) {
            if ($('#bilSalinan' + i).val() == '') {
                $('#bilSalinan' + i).val(1);
            }
        } else {
            var c = document.getElementById("semua");
            if (c.checked)
                c.checked = false;

            if ($('#bilSalinan' + i).val() != null) {
                $('#bilSalinan' + i).val('');
            }
        }
    }

    function clearForm() {
        var url = "${pageContext.request.contextPath}/strata/maklumat_hakmilik_permohonanNew?showFormHm";
        $.post(url,
                function (data) {
                    $('#page_div').html(data);
                }, 'html');
    }


    function resetPAB()
    {

        var r = confirm("Semua rekod PA(B) akan dihapuskan. Anda pasti?");
        if (r == true)
        {
            var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?showFormHm' + param;
            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');

        } else {
            return;
        }
    }

    function removeMultipleMohonHakmilik() {
        if (confirm('Adakah anda pasti untuk hapus hakmilik ini')) {

            var param = '';
            $('.remove2').each(function (index) {
                var a = $('#checkbox' + index).is(":checked");
                if (a) {
                    param = param + '&idMohonHakmilik=' + $('#checkbox' + index).val();
                }
            });

            if (param == '') {
                alert('Sila Pilih Hakmilik terlebih dahulu.');
                return;
            }

            var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?deleteMultipleMohonHakmilik' + param;
            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }

    function selectAll(a, x) {

        var size = '${fn:length(actionBean.hakmilikPermohonanList)}';
        var len = $('.bil').length;

        $(':checkbox').each(function () {
            if (a.checked) {
                this.checked = true;
            } else {
                this.checked = false;
            }
        });

//        alert(x);
//        for (i = x; i < len; i++) {
//            var c = document.getElementById("checkbox" + i);
//            if (c == null)
//                break;
//            c.checked = a.checked;
//        }
//        c.checked = a.checked;
    }
    function perincianTanah(v, w) {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        $.get("${pageContext.request.contextPath}/strata/permohonanStrata?showForm&idHakmilikPihakBerkepentingan=" + v + "&idHakmilik=" + w,
                function (data) {
                    $("#perincianTanah").show();
                    $("#perincianTanah").html(data);
                    $.unblockUI();
                });
    }
    function perincianHakmilik(v) {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });

        $.get("${pageContext.request.contextPath}/strata/maklumat_hakmilik_permohonanNew?showHakmilikStrataReg2&idHakmilik=" + v,
                function (data) {
                    $("#perincianHakmilik").show();
                    $("#perincianHakmilik").html(data);
                    $.unblockUI();
                });

    }

    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function popup() {
        window.open("${pageContext.request.contextPath}/strata/maklumat_hakmilik_permohonanNew?addPelan", "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }

</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.strata.NewStrataMaklumatHakmilikPermohonanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle displaytag">            

        <br /><br />
        <center><p><font color="blue"><b>Jumlah Unit Syer: <s:text name="jumlahUnitSyor" readonly="true" size="8"/>
                    </b></font></p></center>
        <br /> 
        <fieldset class="aras1">  
            <lagend>Maklumat No PA(B) dan No Helaian</lagend> <br/>
            <div class="instr-fieldset">
                <font color="green">Arahan: Sila masukkan Maklumat seterusnya 'Pilih' petak yang terlibat, seterusnya klik butang 'Simpan' </font>
            </div>
            <p align="left">
                <br/><b>No PA(B) &nbsp;&nbsp; :</b>
                <%-- <s:text name="noPelan1" id="noPelan1" onkeyup="validateNumber2(this,this.value);"/>--%>
                <s:text name="noPelan1" id="noPelan1" />


                <br/><b>No Helaian :</b>
                <s:text name="noPu" id="noPu"/>
                <br/><b>No Skim :</b>
                <s:text name="noSkim" id="noSkim"/>
                <br/><b>No Fail PTG :</b>
                <s:text name="noFail" id="noFail"/>
            </p>  
            <br/>
            <p><label>&nbsp;</label><s:button name="simpanNoPAB" id="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;<s:button class="btn" value="Isi Semula" name="resetForm" onclick="clearForm()"/></p>
            <div>
                <br/>
                <fieldset class="aras1"> 
                    <legend>**Tekan butang 'Tambah Pelan' bagi petak yang mempunyai No Pelan melebihi dari 1 pelan  </legend>
                    <legend>**Tekan butang 'Tambah Pelan' dan masukkan Id Hakmilik Induk bagi bangunan yang mempunyai no pelan tambahan tanpa petak (cth: No pelan bumbung)</legend>
                    <br/>

                    <s:button name="addPelan" value="Tambah Pelan" class="btn" onclick="popup();"/>

                </fieldset>
            </div>

            <br/><br/>

            Maklumat Hakmilik Baru <br/><br/><br/>

            <c:if test="${fn:length(actionBean.hakmilikPermohonanList) > 0}">

                <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="sizeHakmilikPermohonan"/>
                <display:table class="tablecloth" style="width:100%;" requestURI="${pageContext.request.contextPath}/strata/maklumat_hakmilik_permohonanNew" name="${actionBean.hakmilikPermohonanBaruList}" cellpadding="0" cellspacing="0" pagesize="150" id="line">

                    <display:column title="Pilih <br/><input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this,${line_rowNum});' class='readonly'/>">
                        <center><s:checkbox name="checkbox${line_rowNum-1}" value="${line.hakmilik.idHakmilik}" id="checkbox${line_rowNum-1}"  class="readonly" /></center>
                        </display:column>

                    <display:column title="No" class ="bil"><center>${line_rowNum}</center></display:column>
                    <display:column title="ID Hakmilik"><center><a href="#" onclick="perincianHakmilik('${line.hakmilik.idHakmilik}');
                            return false;"><c:if test="${line.hakmilik.noPelan eq null}"><font color="red">${line.hakmilik.idHakmilik}</font></c:if><c:if test="${line.hakmilik.noPelan ne null}">${line.hakmilik.idHakmilik}</c:if></a></center>
                                    <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.hakmilik.idHakmilik}"/>
                                    <s:hidden name="hm${line_rowNum-1}" id="hm${line_rowNum-1}" value="${line.hakmilik.idHakmilik}"/>
                                </display:column>
                                <display:column title="No Bangunan">
                                    <c:if test="${line.hakmilik.kodKategoriBangunan.kod ne 'L'}">
                            <center>${line.hakmilik.noBangunan}</center>
                            </c:if>
                        </display:column>
                        <display:column title="No Tingkat">
                            <c:if test="${line.hakmilik.kodKategoriBangunan.kod ne 'L'}">
                            <center>${line.hakmilik.noTingkat}</center>
                            </c:if>
                        </display:column>
                        <display:column title="No Petak">
                            <c:if test="${line.hakmilik.kodKategoriBangunan.kod ne 'L'}">
                            <center>${line.hakmilik.noPetak}</center>
                            </c:if>
                            <c:if test="${line.hakmilik.kodKategoriBangunan.kod eq 'L'}">
                            <center>${line.hakmilik.noPetak}</center>
                            </c:if>
                        </display:column>

                    <display:column title="Unit Syer">
                        <center>${line.hakmilik.unitSyer}</center>
                        </display:column>
                        <display:column title="No PA(B)" style="width:100px">
                        <center>${line.hakmilik.noPelan}</center>
                        </display:column>
                        <display:column title="No Helaian" style="width:160px">
                        <center>${line.hakmilik.noPu}</center>
                        </display:column>
                        <display:column title="No Skim">
                        <center>${line.hakmilik.noSkim}</center>
                        </display:column>
                    <display:column title="Luas" style="width:5px">
                        <center>
                            <s:text name="luas${line_rowNum-1}" id="luas${line_rowNum-1}" value="${line.hakmilik.luas}"/>
                        </center>
                        </display:column>
                </display:table>
                        <p><label>&nbsp;</label><s:button name="simpanLuas" id="simpanLuas" value="Simpan Luas" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
                            </p>
            </c:if>
            &nbsp;
            <br>
        </fieldset>
    </div>
</s:form>
<div id="perincianHakmilik">
</div>



