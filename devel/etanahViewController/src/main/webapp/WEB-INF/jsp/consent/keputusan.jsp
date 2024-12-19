<%-- 
    Document   : status
    Created on : 17-Oct-2009, 00:39:34
    Author     : md.nurfikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<c:set value="false" var="sq"/>
<%--<c:if test="${fn:length(actionBean.tabBean.outcome) > 0 || actionBean.tabBean.isUlasanOnly}">
    <%@include file="/WEB-INF/jsp/keputusan_pendaftar.jsp" %>
    <c:if test="${pendaftar}">        
        <c:set value="true" var="sq"/>
    </c:if>
</c:if>--%>

<style type="text/css">
    .messages { background-color: green; }
</style>
<script type="text/javascript">
    var DELIM = "__^$__";

    $(document).ready(function() {
        //$('#view_delegate').hide();
        var v = '${actionBean.fasaPermohonan.keputusan.kod}';
//       alert(v);
       hideUlasan(v);
    });
    
    function hideUlasan(val) {
        if (val == 'L' || val == 'TQ' || val == '') {
           
            $('#sebab2').hide();
        } else {
             
            $('#sebab2').show();
        }
    }
    
    
    function hideSebabTolak(val) {
        if(val == 'L'){
            $('#sebab2').hide();
        } else  if(val == 'T'){
            $('#sebab2').show();
            alert('Sila masukkan sebab tolak');
        } else  if(val == 'TQ'){
            $('#sebab2').hide();
        }  
    }
    
    function doValidate() {
        
        var f = true;        
        var lulus = $('#lulus').is(':checked');
        var tolak = $('#tolak').is(':checked');
        var tangguh = $('#tangguh').is(':checked');
        
        if(!lulus && !tolak && !tangguh){
            f = false;
        }
        else{
            f = true;
        }
        
//        if(!f){
//            alert('Sila Masukkan Keputusan.');
//            return false;
//        }
        
//        if(tolak){
//            var t = $('#text_sebab').val();
//            if(t == ''){
//                alert('Sila masukkan sebab tolak');
//                return false;
//            }
//        }
        
        //        var t = $('#text_ulasan').val();
        //        var k2 = $('#keputusan2').is(':checked');
        //        var k3 = $('#keputusan3').is(':checked');
        //        if(k2 && t == '' || k3 && t == '')
        //        {
        //            alert('Sila masukkan ulasan');
        //            return false;
        //        }
                           

 
        //        if ( $('#tarikh_keputusan').length > 0) {
        //            if ( $('#tarikh_keputusan').val() == '') {
        //                alert('Sila Masukan tarikh keputusan.');
        //                return false;
        //            }
        //        }
 
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
                    alert(data);
                    $('#status').click();
                } else if (data.indexOf('Dokumen telah dijana') == 0) {
                    alert(data);
                    $('#hantar').removeAttr('disabled');
                    $('#hantar').removeClass('disablebtn');
                    $('#hantar').addClass('btn');
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

<s:useActionBean beanclass="etanah.view.stripes.consent.StatusActionBean" var="penggunaperanan"/>
<s:form beanclass="etanah.view.stripes.consent.StatusActionBean">
    <s:messages/>
    <s:hidden name="stageId"/>
    <s:hidden name="fasaPermohonan.idFasa"/>
    <s:hidden name="permohonan.idPermohonan"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Keputusan
            </legend>
            <p>
                <label><em>*</em>Keputusan :</label>
                <s:radio name="fasaPermohonan.keputusan.kod" id="lulus" value="L" onclick="hideSebabTolak(this.value);"/> Lulus
                <s:radio name="fasaPermohonan.keputusan.kod" id="tolak" value="T" onclick="hideSebabTolak(this.value);"/> Tolak
                <s:radio name="fasaPermohonan.keputusan.kod" id="tangguh" value="TQ" onclick="hideSebabTolak(this.value);"/> Tangguh
                <%--
                                <c:if test="${outcome.value ne 'L' && outcome.value ne 'T' && outcome.value ne 'TQ'}" >
                                    ${outcome.label} &nbsp;
                                </c:if>            
                                <c:if test="${outcome.value eq 'L'}">Lulus  &nbsp;</c:if>
                                <c:if test="${outcome.value eq 'T'}">Tolak  &nbsp;</c:if>
                                <c:if test="${outcome.value eq 'TQ'}">Tangguh  &nbsp;</c:if>--%>
            </p>

            <br/>
            <p id="sebab2">
                <label for="tolak">Sebab Tolak :</label>
                <s:textarea name="permohonan2.ulasan" id="text_sebab" cols="60" rows="10" />
            </p>

            <p id="ulasan2">
                <label for="ulasan">Ulasan :</label>
                <s:textarea name="fasaPermohonan.ulasan" class="normal_text" id="text_ulasan" cols="60" rows="10" />
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
                        <s:button name="save" value="Simpan Dan Jana" class="btn" onclick="doSubmit(this.form, this.name, 'status_div');"/>
                    </p>
                </c:when>
                <c:otherwise>
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="save" value="Simpan" class="longbtn" onclick="if(doValidate())doSubmit(this.form, this.name, 'status_div');"/>
                    </p>
                </c:otherwise>
            </c:choose>
        </fieldset>
    </div>
    <br/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Syor Dan Ulasan
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.listFasa}" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="No" sortable="true" style="vertical-align:top;">${line_rowNum}</display:column >
                    <display:column property="idPengguna" title="Nama" style="vertical-align:top;"/>
                    <display:column property="keputusan.nama" title="Keputusan" style="vertical-align:top;"/>
                    <display:column title="Ulasan" style="width:80px;vertical-align:top;">
                        <textarea name="" style="background: transparent; border: 0px" cols="80" rows="10" readonly="readonly" id="text_${line_rowNum}">${line.ulasan}</textarea>
                    </display:column>
                    <display:column title="Sebab Tolak" style="width:90px;vertical-align:top;">
                        <c:if test="${line.keputusan.kod eq 'T'}">
                            <textarea name="actionBean." style="background: transparent; border: 0px" cols="30" rows="10" readonly="readonly" id="text_${line_rowNum}">${line.permohonan.ulasan}</textarea>
                        </c:if>
                    </display:column>
                    <display:column title="Tarikh Keputusan" style="width:90px;vertical-align:top;">
                        <fmt:formatDate value="${line.tarikhKeputusan}" pattern="dd/MM/yyyy hh:mm:ss"/>
                    </display:column>
                </display:table>
            </div>
        </fieldset>
        <br/>

        <c:if test="${!empty actionBean.listFasaSebelum}">
            <fieldset class="aras1">
                <legend>
                    Syor Dan Ulasan Permohonan Sebelum ( ${actionBean.permohonan.permohonanSebelum.idPermohonan} )
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listFasaSebelum}" cellpadding="0" cellspacing="0" id="lineSblm">
                        <display:column title="No" sortable="true" style="vertical-align:top;">${lineSblm_rowNum}</display:column >
                        <display:column property="idPengguna" title="Nama" style="vertical-align:top;"/>
                        <display:column property="keputusan.nama" title="Keputusan" style="vertical-align:top;"/>
                        <display:column title="Ulasan" style="width:80px;vertical-align:top;">
                            <textarea name="" style="background: transparent; border: 0px" cols="80" rows="10" readonly="readonly" id="text_${lineSblm_rowNum}">
                                ${lineSblm.ulasan}
                            </textarea>
                        </display:column>
                        <display:column title="Tarikh Keputusan" style="width:90px;vertical-align:top;">
                            <fmt:formatDate value="${lineSblm.tarikhKeputusan}" pattern="dd/MM/yyyy hh:mm:ss"/>
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
        </c:if>    
    </div>
</s:form>

