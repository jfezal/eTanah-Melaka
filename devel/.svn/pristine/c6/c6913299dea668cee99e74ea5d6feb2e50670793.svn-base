<%-- 
    Document   : keputusan_jktlm
    Created on : Jun 18, 2013, 4:15:47 PM
    Author     : muhammad.amin
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy', changeMonth:true, changeYear: true});
        var keputusan = '${actionBean.keputusan}';
        hideShowSebab(keputusan)
    });
    
    function hideShowSebab(val) {
        if (val == 'T') {       
            $('#p_sebab').show();
        } else {        
            $('#p_sebab').hide();
        }
    }
    
    function doValidate() {
        
        var f = true;        
        var lulus = $('#lulus').is(':checked');
        var tolak = $('#tolak').is(':checked');
        var tangguh = $('#tangguh').is(':checked');
        var tarikh =  $('#tarikh').val();
        
        if(!lulus && !tolak && !tangguh){
            f = false;
        }
        else{
            f = true;
        }
        
        if(!f){
            alert('Sila Masukkan Keputusan.');
            return false;
        }
        
        else if(tarikh == ''){
            alert('Sila Masukkan Tarikh Keputusan.');
            return false;
        }
        
        return true;
    }

</script>

<s:form beanclass="etanah.view.stripes.consent.KeputusanJktlmActionBean">
    <s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Keputusan</legend>
            <p>
                <label><em>*</em>Keputusan :</label>
                <s:radio name="keputusan" id="lulus" value="L" onclick="hideShowSebab('L');"/> Lulus
                <s:radio name="keputusan" id="tolak" value="T" onclick="hideShowSebab('T');"/> Tolak
                <s:radio name="keputusan" id="tangguh" value="TQ" onclick="hideShowSebab('TQ');"/> Tangguh
            </p>
            <p id="p_sebab">
                <label>Sebab tolak :</label>
                <s:textarea name="permohonan.ulasan" rows="4" cols="70" class="normal_text"/>
            </p>
            <p>
                <label>Ulasan :</label>
                <s:textarea name="fasaPermohonan.ulasan" rows="4" cols="70" class="normal_text"/>
            </p>
            <p>
                <label>Tarikh Keputusan :</label>
                <fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss" value="${actionBean.permohonanRujukanLuar.tarikhSidang}"/>
                <%--  <s:text name="tarikhKeputusan" formatType="date" formatPattern="dd/MM/yyyy" class="datepicker" id="tarikh"/>--%>
            </p>
            <p>
                <label>Persidangan :</label>
                ${actionBean.permohonanRujukanLuar.noSidang}
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(doValidate())doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset>
    </div>
    <br/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Syor Dan Ulasan
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.permohonan.senaraiFasa}" cellpadding="0" cellspacing="0" id="line">
                    <%--       <display:column title="No" sortable="true" style="vertical-align:top;">
                               <c:if test="${line.keputusan ne null}">
                                   ${line_rowNum}
                               </c:if>
                           </display:column >
                    --%>
                    <display:column title="Nama" style="vertical-align:top;">
                        <c:if test="${line.keputusan ne null}">
                            ${line.idPengguna}
                        </c:if>
                    </display:column>
                    <display:column title="Keputusan" style="vertical-align:top;">
                        <c:if test="${line.keputusan ne null}">
                            ${line.keputusan.nama}
                        </c:if>
                    </display:column>
                    <display:column title="Ulasan" style="width:80px;vertical-align:top;">
                        <c:if test="${line.keputusan ne null}">
                            <textarea name="" style="background: transparent; border: 0px" cols="80" rows="10" readonly="readonly" id="text_${line_rowNum}">${line.ulasan}</textarea>
                        </c:if>
                    </display:column>
                    <display:column title="Sebab Tolak" style="width:90px;vertical-align:top;">
                        <c:if test="${line.keputusan ne null}">
                            <c:if test="${line.keputusan.kod eq 'T'}">
                                <textarea name="" style="background: transparent; border: 0px" cols="30" rows="10" readonly="readonly" id="text_${line_rowNum}">${line.permohonan.ulasan}</textarea>
                            </c:if>
                        </c:if>
                    </display:column>
                    <display:column title="Tarikh Keputusan" style="width:90px;vertical-align:top;">
                        <c:if test="${line.keputusan ne null}">
                            <fmt:formatDate value="${line.tarikhKeputusan}" pattern="dd/MM/yyyy hh:mm:ss"/>
                        </c:if>
                    </display:column>
                </display:table>
            </div>
        </fieldset>
    </div>
    <br/>
</s:form>

