<%--
    Document   : pengurusan_dokumen
    Created on : 01 Disember 2009, 5:21:58 PM
    Author     : Rui
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Dokumen Baru
            </legend>
            &nbsp;&nbsp;&nbsp;&nbsp;
            Perhatian : Sila isikan maklumat bagi medan yang bertanda <font color="red"> *</font>
            dibawah dan klik butang 'Simpan' bagi penambahan fail baru.

            <div class="content">
                <p>
                    <label for=""> Muatnaik Dokumen : </label>
                    <s:file name=" "/>
                    <br/>
                    <label>&nbsp;</label>
                    <font color="Green" size="1">Jika anda ingin mengimbas dokumen, kosongkan medan diatas </font>
                </p>
                <p>&nbsp;</p>
                <p></p><hr class="s" size="1">
                 <p>&nbsp;</p>
                <p>
                    <label for=""> Tajuk Fail : </label>
                    &nbsp;
                </p>
                <p>
                    <label for=""> Dokumen : </label>
                    &nbsp;
                </p>
                <p>
                    <label for=""> <font color="Red">*</font> Tajuk Dokumen : </label>
                    <s:text name=" "/>
                </p>
                <p>
                    <label for=""> Catatan Minit : </label>
                    <s:textarea name=" "rows="5" cols="70"/>
                </p>
                <p>
                    <label for=""> Keterangan : </label>
                    <s:textarea name=" "rows="5" cols="70"/>
                </p>
                <p>&nbsp;</p>
            </div>
                
        </fieldset>

        <p>
            <label>&nbsp;</label>
            <s:submit name="" value="Keluar" class="btn"/>
            <s:reset name="" value="Isi Semula" class="btn"/>
            <s:submit name="" value="Simpan" class="btn"/>
        </p>
    </div>

</s:form>