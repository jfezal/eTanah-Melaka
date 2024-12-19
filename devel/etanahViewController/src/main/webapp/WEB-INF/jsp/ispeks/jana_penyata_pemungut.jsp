<%--    
    Document   : speks
    Created on : Jul 22, 2010, 4:21:36 PM
    Author     : amir.muhaimin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<title>Fail SPEKS</title>
<div id="jab">
    <div align="center">
        <table style="width:99.2%" bgcolor="green">
            <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">SPEKS</font>
                    </div></td></tr>
        </table><br>
    </div><br>
    <script type="text/javascript">
        $(document).ready( function(){
            if(dl == null || dl == ''){
                $("#mt").attr("disabled", true);
            }
        });
        
    function click_clear(){
        document.formSPEKS.datepicker.value="";
    }
    </script>
    <stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <stripes:messages />
    <stripes:errors />
    <stripes:form beanclass="etanah.view.stripes.ispeks.BaruPenyataPemungutActionBean" id="formSPEKS" name="formSPEKS">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Fail SPEKS </legend>
                <p class=instr>
                    <em><font color="black">Sila masukkan Tarikh.</font></em>
                </p>
                <br />
                <p>
                    <label>Tarikh :</label>
                    <stripes:text name="tarikh" id="datepicker" onblur="javascript:checking(this.form);" size="20;" class="datepicker" />
                </p>
                 <p>
                    <label>Id Pengguna Kaunter :</label>
                    <stripes:text name="idPenggunaKaunter"  size="20;"/>
                </p>
                 <p>
                    <label>No Resit :</label>
                    <stripes:text name="noResit"  size="20;"/>
                </p>
                <p>
                    <label>No Kaunter :</label>
                    <stripes:text name="noKaunter"  size="20;"/>
                </p>
                <p>
                    <label>Cara Bayar :</label>
                    <stripes:select name="caraBayar">
                        <stripes:option value="0" label="Pilih ..." />
                                <stripes:options-collection collection="${listUtil.senaraiKodCaraBayaran}"  label="nama" value="kod" sort="nama"/>
                            </stripes:select>
                </p>
                <p>
                    <label>Mod Bayaran :</label>
                    <stripes:select name="modBayaran">
                        <stripes:option value="" label="Pilih ..." />
                        <stripes:option value="B" label="Mod Biasa"/>
                        <stripes:option value="L" label="Mod Lewat"/>
                            </stripes:select>
                </p>
                <p>
                    <label>Jumlah Resit :</label>
                    <stripes:text name="limit" size="20;" />

                </p>
                <p> <label>Jumlah  :</label>
                   RM <fmt:formatNumber value="${actionBean.totalAmaun}" pattern="#,##0.00"/>
                </p>
                <br />
                <p>
                    <label>&nbsp;</label>
                    <stripes:submit name="populateResit" value="Papar" class="btn"/>
                    <!-- stripes:button name="reset" value="Isi Semula" class="btn" onclick="click_clear();"/ -->
                    <%--<stripes:submit id="mt" name="downloadFile" value="Muatturun" class="btn"/>--%>
                </p>
                <br />
            </fieldset>
                <fieldset class="aras1">
                <legend>Fail SPEKS </legend>
                <p class=instr>
<!--                    <em><font color="black">Sila masukkan Tarikh.</font></em>-->
                </p>
               <p>
                        <display:table style="width:100%" class="tablecloth" name="${actionBean.senaraiResitPenyataPemungut}"  cellpadding="0" cellspacing="0" id="line">
                            <display:column title="Bil">${line_rowNum} <stripes:hidden name="idKewDok">${line.idKewDok}</stripes:hidden></display:column>
                            <display:column title="Hapus" style="width:5%"><stripes:checkbox name="exclude" value="${line.idKewDok}"></stripes:checkbox></display:column>
                            <display:column title="No Resit" property="idKewDok"/>
                            <display:column title="Tarikh Resit" property="trhResit"/>
                            <display:column title="Amaun"  style="text-transform:uppercase;"><fmt:formatNumber value="${line.amaun}" pattern="#,##0.00"/></display:column>
                            <display:column title="Id Kaunter" property="idPenggunaKaunter"/>
                            <display:column title="No Kaunter" property="noKaunter"/>
                        </display:table>
                    </p>
            </fieldset>
                     <p>
                    <label>&nbsp;</label>
                    <stripes:submit name="simpanPenyata" value="Jana" class="btn"/>
                    <!-- stripes:button name="reset" value="Isi Semula" class="btn" onclick="click_clear();"/ -->
                </p>
        </div>
    </stripes:form>
</div>


    