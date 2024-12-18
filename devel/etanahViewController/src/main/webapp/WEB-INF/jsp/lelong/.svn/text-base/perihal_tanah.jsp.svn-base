<%-- 
    Document   : perihal_tanah
    Created on : Mar 4, 2010, 6:39:33 PM
    Author     : mazurahayati
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">

    function validate(){
        var a = ${A};
        var b = ${B};
        if(b){
            if($('#perihal').val() == ""){
                alert("Sila Masukkan Perihal Tanah");
                $('#perihal').focus();
                return false;
            }
            if($('#perihalBI').val() == ""){
                alert("Sila Masukkan Perihal Tanah");
                $('#perihalBI').focus();
                return false;
            }
        }
        
        if(a){
            var bil = $(".rowCount").length;
            for (var i = 0; i < bil; i++){
                if($('#perihal'+i).val() == ""){
                    alert("Sila Masukkan Perihal Tanah");
                    $('#perihal'+i).focus();
                    return false;
                }
                if($('#perihalBI'+i).val() == ""){
                    alert("Sila Masukkan Perihal Tanah");
                    $('#perihalBI'+i).focus();
                    return false;
                }
            }   
        }
        return true;
    }

    function showReport(frm,event){

        if(validate()){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var url = frm.action + '?' + event;
            var queryString = $(frm).formSerialize();
            $.ajax({
                type:"POST",
                url : url,
                dataType : 'html',
                data : queryString,
                error : function(xhr, ajaxOptions, thrownError) {
                    alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                    $.unblockUI();
                },
                success : function(data) {
                    alert(data);
                    $('#folder').click();
                    $.unblockUI();
                }
            });
        }
    }

</script>
<s:form beanclass="etanah.view.stripes.lelong.PrisytiharanJualanActionBean">

    <s:messages/>
    <s:errors/>&nbsp;

    <fieldset class="aras1">
        <legend>
            Perihal Tanah
        </legend>

        <c:if test="${B eq true}">
            <br>
            <p>
                <label><font color="red">*</font> Perihal Tanah : </label>
                <s:textarea id="perihal" name="perihal" cols="50" rows="5"/>

            </p>
            <c:if test="${actionBean.negeri eq true}">
                <p>
                    <label><font color="red">*</font> Perihal Tanah Bahasa Inggeris : </label>
                    <s:textarea id="perihalBI" name="perihalBI" cols="50" rows="5"/>
                </p>
            </c:if>
        </c:if>

        <c:if test="${A eq true}">
            <br>
            <div align="center">
                <display:table class="tablecloth" name="${actionBean.listLelongan}" id="line">
                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                    <display:column title="IDHakmilik" class="rowCount" property="hakmilikPermohonan.hakmilik.idHakmilik" />
                    <display:column title="Perihal Tanah">
                        <s:textarea id="perihal${line_rowNum - 1}" name="listLelongan[${line_rowNum - 1}].perihalTanah" cols="50" rows="5"/>
                    </display:column>
                    <c:if test="${actionBean.negeri eq true}">
                        <display:column title="Perihal Tanah Bahasa Inggeris" >
                            <s:textarea id="perihalBI${line_rowNum - 1}" name="listLelongan[${line_rowNum - 1}].perihalTanahBahasaInggeris" cols="50" rows="5"/>
                        </display:column>
                    </c:if>
                </display:table>
            </div>
        </c:if>
        <br>
        <p align="center">
            <s:button name="simpanPerihal" value="Simpan Dan Jana" class="longbtn"  onclick="showReport(this.form,this.name);"/>
            <c:if test="${semak16H eq false}">
                <s:button name="kembali" value="Isi Semula" class="btn"  onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </c:if>
        </p>
    </fieldset>
</s:form>