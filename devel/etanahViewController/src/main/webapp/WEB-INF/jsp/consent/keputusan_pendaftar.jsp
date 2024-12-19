<%--
    Document   : status
    Created on : 16-May-2013, 10:49:13
    Author     : nik.muhammad
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

    $(document).ready(function(){
        
        $('#ulasan2').hide();
   
    });

   
    function hideSebabTolak(val) {
        if(val == 'L'){
            $('#text_tolak').hide();
            $('#sebabtolak').hide();
        } else  if(val == 'T'){
            $('#sebabtolak').show();
            $('#text_tolak').show();
            alert('Sila masukkan sebab tolak');
        } else  if(val == 'TQ'){
            $('#sebabtolak').hide();
            $('#text_tolak').hide();
        }  
    }
    
    function hideUlasan(val) {
        if(val == 'D'){
            $('#text_ulasan').val('');
            $('#ulasan').hide();
        } else {
            $('#ulasan').show();
        }      
    }
    
    function doValidate(){
        var f = true;
        var l = document.getElementsByName('fasaPermohonan.keputusan.kod').length;
        for(var i=1; i<=l; i++){
            var v = $('#keputusan'+i).is(':checked');
      
            if(!v) {
                f = false;
            } else{
                f = true;
                break;
            }
        }
        
        var t = $('#text_ulasan').val();
        var k2 = $('#keputusan2').is(':checked');
        var k3 = $('#keputusan3').is(':checked');
        if(k2 && t == '' || k3 && t == '')
        {
            alert('Sila masukkan ulasan');
            return false;
        }
                           
      
            
        if(!f){
            alert('Sila Masukkan Keputusan.');
            return false;
        }

        if ( $('#tarikh_keputusan').length > 0) {
            if ( $('#tarikh_keputusan').val() == '') {
                alert('Sila Masukan tarikh keputusan.');
                return false;
            }
        }

        return true;
    }

    function saveAndGenerateReport(frm){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        
        
            
        var queryString = $(frm).formSerialize();
        var url = '${pageContext.request.contextPath}/keputusan?save&saveAndGenerateReport=true';
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            //timeout: 500,
            data : queryString,
            
            
            error : function(xhr, ajaxOptions, thrownError) {
                //alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                $("#status_div").html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t><a href="#">[ Lihat Terperinci ]</a></div><br/><div id=error class=b>' + xhr.responseText + '</div>');
                $.unblockUI();
                
                
            },
            success : function(data) {
                if(data.indexOf('Tiada keputusan')==0){
                    alert(data);
                    $('#status').click();
                }else if(data.indexOf('Dokumen telah dijana')==0){
                    alert(data);
                    $('#hantar').removeAttr('disabled');
                    $('#hantar').removeClass('disablebtn');
                    $('#hantar').addClass('btn');
                    $('#folder').click();
                }else if(data.indexOf('dhde') == 0){
                    var p = data.split(DELIM);
                    var id = p[1];
                    var url = '${pageContext.request.contextPath}/dokumen/view/' + id +'?signForm';
                    window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
                    //doViewReport(id);
                }else {
                    alert(data);
                    $('#urusan').click();
                }
                $.unblockUI();
            }

        });
    }

    function doViewReport(id){
        if(id != ''){
            var url = '${pageContext.request.contextPath}/dokumen/view/' + id +'?signForm';
            alert(url);
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
    }


    function viewDelegate(){
        $('#view_delegate').show();
    }

    function doAgih(e, f) {
        var i = $('#id_pguna').val();
        if(i == ''){
            alert('Sila pilih pengguna terlebih dahulu.');
            $('#id_pguna').focus();
            return false;
        }
        if(confirm('Adakah anda pasti?')) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var q = $(f).formSerialize();
            f.action = f.action + '?' + e + '&' + q;
            f.submit();
        }
    }

    function doCopyUlasan(id){
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
                <c:if test="${fn:length(actionBean.tabBean.outcome) > 0}">
                    <p>
                    <label><em>*</em>Keputusan :</label>
                    <c:forEach items="${actionBean.tabBean.outcome}" var="outcome" varStatus="line">
                        <s:radio name="fasaPermohonan.keputusan.kod" id="keputusan${line.count}" value="${outcome.value}" onclick="hideSebabTolak(this.value);"/>
                        <c:if test="${outcome.value ne 'L' && outcome.value ne 'T' && outcome.value ne 'TQ'}" >
                        ${outcome.label} &nbsp;
                        </c:if>            
                        <c:if test="${outcome.value eq 'L'}">Lulus  &nbsp;</c:if>
                        <c:if test="${outcome.value eq 'T'}">Tolak  &nbsp;</c:if>
                        <c:if test="${outcome.value eq 'TQ'}">Tangguh  &nbsp;</c:if>
                    </c:forEach>
                    </p>
                </c:if>
                
                 <p id="sebabtolak">
                     <label for="sebabtolak">Sebab Tolak :</label>
                <s:textarea name="permohonan.ulasan" id="text_tolak" cols="60" rows="10" value="${actionBean.permohonan.ulasan}" />
            </p>
            
            <p id="ulasan">
                <label for="ulasan">Ulasan :</label>
                <s:textarea name="fasaPermohonan.ulasan" id="text_ulasan" cols="60" rows="10" value="${actionBean.fasaPermohonan.ulasan}" />
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
                        <c:choose>
                            <c:when test="${reportGenerated}">
                                <c:choose>
                                    <c:when test="${!berangkai}">
                                        <s:button name="save" value="Simpan Dan Jana" class="longbtn"
                                                  onclick="if(doValidate())saveAndGenerateReport(this.form);"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${!finalStage}">
                                            <s:button name="save" value="Simpan Dan Jana" class="longbtn"
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
                                <s:button name="save" value="Simpan" class="longbtn" onclick="if(doValidate())doSubmit(this.form, this.name, 'status_div');"/>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${actionBean.kodJabatan eq '16' && finalStage}">
                            <s:button name="copyUlasan" value="Salin Ulasan PT" class="longbtn" id="copyUlasan" onclick="doCopyUlasan('${fn:length(actionBean.listFasa)}')"/>
                        </c:if>
                    </p>
                </c:otherwise>
            </c:choose>
        </fieldset>
    </div>
</s:form>