<%-- 
    Document   : sedia_surat_badan_pengawal
    Created on : Oct 19, 2011, 10:56:07 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function validateNumeric(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            return true;
        }else{

            return false;
        }
    }
        
    jQuery.fn.ForceNumberOnly = function() {
        return this.each(function()     {
            $(this).keydown(function(e)         {
                var key = e.charCode || e.keyCode || 0;             // allow backspace, tab, delete, arrows, numbers and keypad numbers ONLY
                return (
                key == 8 ||
                    key == 9 ||
                    key == 46 ||
                    (key >= 37 && key <= 40) ||
                    (key >= 48 && key <= 57) ||
                    (key >= 96 && key <= 105));
            });
        });
    };
    jQuery.fn.ForceNumericOnly = function() {
        return this.each(function()     {
            $(this).keydown(function(e)         {
                var key = e.charCode || e.keyCode || 0;              
                return (
                key == 8 ||
                    (key >= 64 && key <= 91) ||
                    (key >= 96 && key <= 123));
            });
        });
    };
    jQuery('.numbersOnly').ForceNumberOnly();
    jQuery('.numericsOnly').ForceNumericOnly();
    function test(f) {
        $(f).clearForm();
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.stripes.pelupusan.SuratBadanPengawalActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend align="justify">Surat Badan Pengawal</legend>
            <br>
            <p>
                <label>Jabatan Pengawal Rizab :</label>
                <c:if test="${actionBean.tanahRizabPermohonan ne null}">
                    <c:if test="${actionBean.tanahRizabPermohonan.penjaga eq null}">
                        <s:text name="tanahRizabPermohonan.penjaga" size="30"/>
                    </c:if>
                    <c:if test="${actionBean.tanahRizabPermohonan.penjaga ne null}">
                        <s:text name="tanahRizabPermohonan.penjaga" size="30"/>
                    </c:if>
                </c:if>
                <c:if test="${actionBean.tanahRizabPermohonan eq null}">
                    <s:text name="tanahRizabPermohonan.penjaga" size="30"/>
                </c:if>
            </p>
            <p>
                <label>Nama Pegawai Pengawal :</label>
                <s:text name="tanahRizabPermohonan.namaPenjaga" size="50"/>
            </p>
            <p>
                <label>Alamat :</label>
                <s:text name="tanahRizabPermohonan.jagaAlamat1" size="30"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="tanahRizabPermohonan.jagaAlamat2" size="30"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="tanahRizabPermohonan.jagaAlamat3" size="30"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="tanahRizabPermohonan.jagaAlamat4" size="30"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="tanahRizabPermohonan.jagaPoskod" size="10" maxlength="5" class="numbersOnly" />
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="kodNegeri" id="negeri" value="${actionBean.tanahRizabPermohonan.jagaNegeri.kod}">
                    <s:option value="0">Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
            <p align="center">
                <s:button name="simpanBadanPengawal" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                <s:button name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
            </p>
        </fieldset>
    </div>
</s:form>

