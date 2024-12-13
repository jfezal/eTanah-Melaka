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
        $(document).ready(function () {
            if (dl == null || dl == '') {
                $("#mt").attr("disabled", true);
            }
        });

        function click_clear() {
            document.formSPEKS.datepicker.value = "";
        }
            function papar(noPenyata){

        var report =null;

  
            report = 'HSL_PP_SPEKS_MLK.rdf';
//                          var url = "reportName=" + report + "%26report_p_id_hakmilik=" + param.value;

        var url = "reportName="+report+"%26report_p_id_pp="+noPenyata;

        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");

    <%--window.open("${pageContext.request.contextPath}/reportGeneratorServlet?reportName="+report+"&report_p_id_kew_dok="+resit, "eTanah",
    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700")--%>
        }
    </script>
    <stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <stripes:messages />
    <stripes:errors />
    <stripes:form beanclass="etanah.view.stripes.ispeks.SediaJournalActionBean" id="formSPEKS" name="formSPEKS">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Fail SPEKS </legend>

                <br />
                <p>
                    <label>Jenis Jurnal:</label>
<stripes:select name="jenisJournal" id="_kodDaerah" >
                        <stripes:option value="">-- Sila Pilih --</stripes:option>
                        <stripes:options-collection collection="${actionBean.listKodJurnal}" label="jenis" value="id"></stripes:options-collection>
                    </stripes:select>                </p>
             
                <p>
                    <label>No Rujukan :</label>
                    <stripes:text name="noRujukan" size="20;" />

                </p>
                <p>
                    <label>Tarikh Rujukan :</label>
                    <stripes:text name="tarikhRujukan"id="datepicker" class="datepicker" size="20;" />

                </p>
   <p>
                    <label>Perihal :</label>
                    <stripes:textarea name="perihal" />

                </p>
                <br />
                <p>
                    <label>&nbsp;</label>
                    <stripes:submit name="batal" value="Batal" class="btn"/>
                    <stripes:submit name="simpanCipta" value="Simpan" class="btn"/>

                </p>
                <br />
            </fieldset>
        
        </div>
    </stripes:form>
</div>


