<%-- 
    Document   : Borang_4D
    Created on : May 13, 2010, 9:40:58 AM
    Author     : sitifariza.hanim
               -modified by nurul izza on 05102010
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function test(f) {
        $(f).clearForm();
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.stripes.pelupusan.Borang4DActionBean" id="clear">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend align="justify">Permit Tanah Pertanian</legend>
            <br>
            <p>
                <label>No.Permit :</label>
                <s:text name="lupusPermit.noPermit" size="20" maxlength="10" />
                <s:hidden name="lupusPermit.id"/>
            </p>
            <p>
                <label>Tarikh Dikeluarkan :</label>
                <s:text formatPattern="dd/MM/yyyy" name="lupusPermit.tarikhKeluar" class="datepicker" id="tarikh"/>
            </p>
              <p>
                <label>Bayaran :</label>
                RM ${actionBean.mohonTuntutKos.amaunTuntutan}
            </p>

        </fieldset>
        <fieldset class="aras1">
            <legend> Jadual</legend>
            <p>
                <label>Tarikh mula :</label>
                <s:text formatPattern="dd/MM/yyyy" name="lupusPermit.tarikhMula" class="datepicker" id="tarikhMula" />
            </p>
             <p>
                 <label>Tarikh tamat :</label>
                <s:text formatPattern="dd/MM/yyyy" name="lupusPermit.tarikhTamat" class="datepicker" id="tarikhTamat"/>
            </p>
            <p>
                <label>Tujuan Permit :</label>
                <s:text name="lupusPermit.strukturBolehBina" size="50" maxlength="30" />
            </p>
             <p>
                <label>Peruntukan tambahan :</label>
                <s:textarea name="lupusPermit.peruntukanTambahan" cols="47" />
            </p>
            <label>&nbsp;</label>
            <p>
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                <s:button name="reset" value="Isi Semula"   class="btn" onclick="return test();"/>
                <%--<s:button id="reset" value="Isi Semula" name="" class="btn" onclick="clearText('clear');"/>--%>
            </p>
        </fieldset>
    </div>
</s:form>
