<%-- 
    Document   : report_text_file
    Created on : Mar 10, 2015, 3:18:24 PM
    Author     : haqqiem
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
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
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
    }

    function kemaskiniKATG2(kodSyarat) {
        alert(kodSyarat);
        window.open("${pageContext.request.contextPath}/utility/kemaskini?kemaskiniView&kodSyarat=" + kodSyarat, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1500,height=800,scrollbars=yes");
    }
</script>


<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.utility.KemaskiniSyaratNyataHasilActionBean" id="kemaskini" name="form">
    <div id="daerah">
        <s:messages/>
        <s:errors/>
        <s:hidden name="reportNo" />
        <div class="subtitle">
             &nbsp; &nbsp;  Nota :  &nbsp; <img src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem' > Belum Dikemaskini ( Perlu Dikemaskini ) &nbsp; &nbsp;
                <img src='${pageContext.request.contextPath}/pub/images/ok.png' class='rem' > Sudah Dikemaskini ( Boleh Dikemaskini Semula ) <br><br>
            <fieldset class="aras1">
                
                 <fieldset class="aras1">

                       
                        <br>
                        <p><label for="idPermohonan">Kod Syarat Nyata :</label>
                        <s:text name="syarat" id="syarat" onkeyup="this.value=this.value.toUpperCase();" />
                    </p>

                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="CariKodSyarat" value="Cari" class="btn" />
                    </p>
                    <br>
                </fieldset>
              
                <legend>Syarat Nyata</legend>
                <br><br>
                <div class="subtitle" align="center">
                    <fieldset class="aras1">
                        <display:table name="${actionBean.senaraiSyaratNyata}"  class="tablecloth"  requestURI="/utility/kemaskini"
                                       pagesize="10" cellpadding="0" cellspacing="0" id="line">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Syarat Nyata" property="syarat" sortable="true"/>
                            <display:column title="Cawangan" property="cawangan.name"/>


                            <display:column title="Kemaskini">
                                <center>
                                    <c:if test="${line.kategoriHasil eq null}">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             onclick="kemaskiniKATG('${line.kod}');" onmouseover="this.style.cursor = 'pointer';">
                                    </c:if>
                                    <c:if test="${line.kategoriHasil ne null}">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/ok.png' class='rem'
                                             onclick="kemaskiniKATG('${line.kod}');" onmouseover="this.style.cursor = 'pointer';"> 
                                    </c:if>
                                </center>
                                </display:column>
                        </display:table>
                    </fieldset>
                </div>
            </fieldset>
        </div><br>

    </s:form>
</div>
