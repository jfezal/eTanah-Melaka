<%-- 
    Document   : report_text_file
    Created on : Mar 10, 2015, 3:18:24 PM
    Author     : haqqiem
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<%-- carian hakmilik start--%>

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        var daerah = document.getElementById('d').value;
        var rep = document.getElementById('namaReport').value;
        report(rep);
        if ((daerah != 0) || (rep != 0))
            $('#param').show();
        else
            $('#param').hide();
    });



    function selectAll(a) {
        var len = $('.cetaks').length;
        alert("Data = " + len);
        for (var i = 1; i <= len; i++) {
            var c = document.getElementById("cetak" + i);
            c.checked = a.checked;
        }
    }


    function kemaskiniKATG(kodSyarat) {
        window.open("${pageContext.request.contextPath}/utility/kemaskini?kemaskiniView&kodSyarat=" + kodSyarat, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

    function simpanKemaskini(kodSyarat)
    {
        var frm = document.form1;
        var url = "${pageContext.request.contextPath}/utility/kemaskini?simpanKemaskini&idPermohonan=" + kodSyarat;
        frm.action = url;
        frm.submit();

    }
</script>


<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.utility.KemaskiniSyaratNyataHasilActionBean" id="kemaskini" name="form">
    <div id="daerah">
        <s:messages/>
        <s:errors/>
        <s:hidden name="reportNo" />
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Syarat Nyata</legend>
                <div class="subtitle" align="center">
                    <fieldset class="aras1">
                        <display:table name="${actionBean.kodSyaratNyata}"  class="tablecloth"  requestURI="/utility/kemaskini"
                                       pagesize="5" cellpadding="0" cellspacing="0" id="line">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Syarat Nyata" property="syarat" sortable="true"/>
                            <display:column title="Cawangan" property="cawangan.name"/>

                            <display:column title="Kemaskini">
                                <s:select name="katg" style="width:300px;" id="kod">
                                    <s:option value="">-- Sila Pilih --</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" value="kod" label="nama" />
                                </s:select>
                            </display:column>

                        </display:table>
                        <br>
                        <center>
                            <s:hidden name="kodSyarat" id="kodSyarat" value="${line.kod}"/>
                            <s:submit name="simpanKemaskini" value="Simpan" class="btn" />   
                        </center>

                    </fieldset>
                </div>
            </fieldset>
        </div><br>

    </s:form>
</div>
