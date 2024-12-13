<%--
    Document   : status
    Created on : 15-Sep-2009, 10:49:13
    Author     : md.nurfikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
    .messages { background-color: green; }
</style>
<script type="text/javascript">
    var DELIM = "__^$__";

    $(document).ready(function() {
        //$('#view_delegate').hide();
        var v = '${actionBean.fasaPermohonan.keputusan.kod}';
//        hideUlasan(v);
    });

    function hideUlasan(val) {
        if (val == 'D') {
            $('#text_ulasan').val('');
            $('#ulasan').hide();
        } else {
            $('#ulasan').show();
        }


    }
    function doValidate() {
        var f = true;
        var l = document.getElementsByName('fasaPermohonan.keputusan.kod').length;
        for (var i = 1; i <= l; i++) {
            var v = $('#keputusan' + i).is(':checked');

            if (!v) {
                f = false;
            } else {
                f = true;
                break;
            }
        }

        var t = $('#text_ulasan').val();
        var k3 = $('#keputusan3').is(':checked');
        if (${actionBean.permohonan.kodUrusan.kod ne 'RLTB'}) {
            var k2 = $('#keputusan2').is(':checked');
        }
        if (k2 && t == '' || k3 && t == '')
        {
            alert('Sila masukkan ulasan');
            return false;
        }



        if (!f) {
            alert('Sila Masukan Keputusan.');
            return false;
        }

        if ($('#tarikh_keputusan').length > 0) {
            if ($('#tarikh_keputusan').val() == '') {
                alert('Sila Masukan tarikh keputusan.');
                return false;
            }
        }

        return true;
    }

    function saveAndGenerateReport(frm) {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });



        var queryString = $(frm).formSerialize();
        var url = '${pageContext.request.contextPath}/keputusan?save&saveAndGenerateReport=true';
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            //timeout: 500,
            data: queryString,
            error: function(xhr, ajaxOptions, thrownError) {
                //alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                $("#status_div").html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t><a href="#">[ Lihat Terperinci ]</a></div><br/><div id=error class=b>' + xhr.responseText + '</div>');
                $.unblockUI();


            },
            success: function(data) {
                if (data.indexOf('Tiada keputusan') == 0) {
                    if (${actionBean.permohonan.kodUrusan.kod eq 'RTPS' || actionBean.permohonan.kodUrusan.kod eq 'RTHS'}) {
                        alert("Maaf. Di Peringkat Semak Laporan, pengguna memilih untuk ditolak. Maka di peringkat Keputusan PTG, pengguna perlu memilih tolak sahaja.\n\nJika pengguna ingin memilih lulus, pengguna perlu menghantar kembali permohonan dengan menekan butang Semak Semula.");
                        $('#status').click();
                    } else {
                        alert(data);
                        $('#status').click();
                    }
                } else if (data.indexOf('Dokumen telah dijana') == 0) {
                    alert(data);
//                    $('#hantar').removeAttr('disabled');
//                    $('#hantar').removeClass('disablebtn');
//                    $('#hantar').addClass('btn');
                    $('.hantar').each(function() {
                        $(this).removeAttr('disabled');
                        $(this).removeAttr('disablebtn');
                        $(this).addClass('btn');
                    });
                    $('#folder').click();
                } else if (data.indexOf('dhde') == 0) {
                    var p = data.split(DELIM);
                    var id = p[1];
                    var url = '${pageContext.request.contextPath}/dokumen/view/' + id + '?signForm';
                    window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
                    //doViewReport(id);
                } else {
                    alert(data);
                    $('#urusan').click();
                }
                $.unblockUI();
            }

        });
    }

    function doViewReport(id) {
        if (id != '') {
            var url = '${pageContext.request.contextPath}/dokumen/view/' + id + '?signForm';
            alert(url);
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
    }


    function viewDelegate() {
        $('#view_delegate').show();
    }

    function doAgih(e, f) {
        var i = $('#id_pguna').val();
        if (i == '') {
            alert('Sila pilih pengguna terlebih dahulu.');
            $('#id_pguna').focus();
            return false;
        }
        if (confirm('Adakah anda pasti?')) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            var q = $(f).formSerialize();
            f.action = f.action + '?' + e + '&' + q;
            f.submit();
        }
    }

    function doCopyUlasan(id) {
        //if (id != '1') id = '1';
        $('#text_ulasan').focus();
        $('#text_ulasan').val($('#text_1').val());
    }

</script>
<s:useActionBean beanclass="etanah.view.stripes.StatusActionBean" var="penggunaperanan"/>
<s:form beanclass="etanah.view.stripes.StatusActionBean">
    <c:set var="title" value="Keputusan"/>
    <c:choose>
        <c:when test="${fn:toLowerCase(actionBean.tabBean.keputusanTitle) eq 'keputusan'}">
            <c:set var="title" value="Keputusan"/>
        </c:when>
        <c:otherwise>
            <c:set var="title" value="Syor Keputusan"/>
        </c:otherwise>
    </c:choose>
    <s:messages/>
    <s:hidden name="stageId"/>
    <s:hidden name="fasaPermohonan.idFasa"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                ${title}
            </legend>
            <c:if test="${fn:length(actionBean.tabBean.outcome) > 0}">
                <p>
                    <label><em>*</em>${title} :</label>
                    <c:forEach items="${actionBean.tabBean.outcome}" var="outcome" varStatus="line">
                        <%--<c:set var="checked"/>
                        <c:if test="${actionBean.fasaPermohonan.keputusan eq outcome.value}">
                            <c:set var="checked" value="true"/>
                        </c:if>
                        <s:radio name="fasaPermohonan.keputusan.kod" value="${outcome.value}" checked="${checked}"/>${outcome.label} &nbsp; onclick="hideUlasan(this.value);"--%>
                        <s:radio name="fasaPermohonan.keputusan.kod" id="keputusan${line.count}" value="${outcome.value}" />
                        ${outcome.label} &nbsp;
                        <%--
                        <c:if test="${outcome.value ne 'D' && outcome.value ne 'T' && outcome.value ne 'G'}" >
                            ${outcome.label} &nbsp;
                        </c:if>            
                        <c:if test="${outcome.value eq 'D'}">Daftar  &nbsp;</c:if>
                        <c:if test="${outcome.value eq 'T'}">Tolak  &nbsp;</c:if>
                        <c:if test="${outcome.value eq 'G'}">Gantung  &nbsp;</c:if>
                        --%>
                    </c:forEach>
                </p>
            </c:if>
            <p id="ulasan">
                <label for="ulasan"><font color="red">*</font>Ulasan :</label>
                        <s:textarea name="fasaPermohonan.ulasan" class="normal_text" id="text_ulasan" cols="60" rows="10" value="${actionBean.fasaPermohonan.ulasan}" />
            </p>
            <c:if test="${modifyDate}">
                <p>
                    <label for="ulasan"><em>*</em>Tarikh Keputusan :</label>
                    <s:text name="tarikhKeputusan" formatType="date" formatPattern="dd/MM/yyyy" class="datepicker" id="tarikh_keputusan"/>
                </p>
            </c:if>
            <br/>
            <c:choose>
                <c:when test="${actionBean.tabBean.isUlasanOnly}">
                    <p>
                        <label>&nbsp;</label>
                        <%-- <s:button name="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'status_div');"/>--%>
                        <s:button name="save" value="Simpan Dan Jana1" class="btn" onclick="doSubmit(this.form, this.name, 'status_div');"/>
                    </p>
                </c:when>
                <c:otherwise>
                    <p>
                        <label>&nbsp;</label>
                        <c:choose>
                            <c:when test="${reportGenerated}">
                                <c:choose>
                                    <c:when test="${!berangkai}">
                                        <s:button name="save" value="Simpan Dan Jana2" class="longbtn"
                                                  onclick="if(doValidate())saveAndGenerateReport(this.form);"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${!finalStage}">
                                            <s:button name="save" value="Simpan Dan Jana3" class="longbtn"
                                                      onclick="if(doValidate())saveAndGenerateReport(this.form);"/>
                                        </c:if>
                                        <c:if test="${finalStage}">
                                            <s:button name="save" value="Simpan" class="btn"
                                                      onclick="if(doValidate())doSubmit(this.form, this.name, 'status_div');"/>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>


                            <c:otherwise>
                                <%--<s:button name="save" value="Simpan" class="btn" onclick="if(doValidate())doSubmit(this.form, this.name, 'status_div');"/>--%>
                                <!--3/3/2015 cc mintak tukar add button simpan dan jana untuk aik tarikh hakmilik dijana-->
                                <%--<s:button name="save" value="Simpan" class="longbtn" onclick="if(doValidate())doSubmit(this.form, this.name, 'status_div');"/>--%>
                                <c:if test="${actionBean.kodJabatan eq '16' && finalStage}">
                                    <s:button name="save" value="Simpan Dan Jana4" class="longbtn" onclick="if(doValidate())saveAndGenerateReport(this.form);"/>
                                </c:if>
                                <c:if test="${actionBean.kodJabatan ne '16'}">
                                     <s:button name="save" value="Simpan" class="btn" onclick="if(doValidate())doSubmit(this.form, this.name, 'status_div');"/>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${actionBean.kodJabatan eq '16' && finalStage}">
                            <s:button name="copyUlasan" value="Salin Ulasan PT" class="longbtn" id="copyUlasan" onclick="doCopyUlasan('${fn:length(actionBean.listFasa)}')"/>
                        </c:if>
                    </p>
                </c:otherwise>
            </c:choose>
        </fieldset>
        <%--
                    <br>
                    <legend>
                        Delegasi Tugas
                    </legend>
                    <br>
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="save" value="Delegasi" class="btn" onclick="viewDelegate();"/>
                    </p>
                </fieldset>

        <div class="subtitle" id="view_delegate">
            <fieldset class="aras1">
                <br>
                <p><label>Delegasi Tugas  Kepada :</label>
                    <s:select name="pguna.idPengguna" style="width:300px;" id="id_pguna">
                        <s:option value="">-- Sila Pilih --</s:option>
                        <s:options-collection collection="${penggunaperanan.listPp}" value="idPengguna" label="nama" />
                    </s:select>
                </p>
                <br/>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="agihPT" value="Pindah" class="btn" onclick="doAgih(this.name, this.form);"/>
                </p>
            </fieldset>
        </div>--%>

    </div>

</s:form>