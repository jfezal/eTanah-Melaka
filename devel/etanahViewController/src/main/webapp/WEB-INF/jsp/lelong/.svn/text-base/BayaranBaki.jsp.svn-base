<%-- 
    Document   : BayaranBaki
    Created on : Sep 17, 2010, 3:52:46 PM
    Author     : mazurahayati.yusop
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<script type="text/javascript">

    function showReport(){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var url = '${pageContext.request.contextPath}/lelong/bayaranbaki?cetak';
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function(xhr, ajaxOptions, thrownError) {
                alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                   },
                success : function(data) {
                    if(data.indexOf('Laporan telah dijana')==0){
                        alert(data);
                        $('#folder').click();
                    }else {
                        alert(data);
                        $('#urusan').click();
                    }
                    $.unblockUI();
                }
            });
        }
    $(document).ready(function(){
        $('#cetakSP').hide();
        var sdate1 = new Date();
            var sdate6 = new Date().val();
            var vsplit1 = sdate6.split('/');
            var fulldate1 = vsplit1[1]+'/'+vsplit1[0]+'/'+vsplit1[2];
            var date6 = new Date(fulldate1);

            });
            function kire(v,num){
                var now = new Date();
                var now1 = Date.parse(now);
                var ld = v.substring(0,10);
                var date2 = ld.split("-");
                var date3 = date2[1] +"/"+date2[2]+"/"+date2[0]
                var date4 = Date.parse(date3);
                var minutes=1000*60;
                var hours=minutes*60;
                var days=hours*24;
                var years=days*365;
                var d=(now1-date4)/days;
                var hari = Math.round(d);
                if (hari < 0){
                    $('#test'+num).val("Lelongan belum dijalankan");
                }else{
                    $('#test'+num).val(hari);
                }
            }
</script>

<s:form beanclass="etanah.view.stripes.lelong.BayaranBakiActionBean" id ="selenggara_hakmilik">
    <s:messages />
    <s:errors />
<%--<c:if test="${actionBean.flag}">--%>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Carian
            </legend>
            <p>
                <label> ID Permohonan :</label>
                <s:text name="idPermohonan" maxlength="20" size="31" onblur="this.value=this.value.toUpperCase();"/>
            </p>

            <p align="right">
                <s:submit name="find" value="Cari" class="btn" />
                <%--<s:button name=" " value="Isi Semula" class="btn" onclick="clearText('BayaranBaki');" />--%>
            </p>
        </fieldset>
    </div>
    <c:if test="${actionBean.flag eq true}">
        <br>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Senarai IdPermohonan yang belum bayar baki
                </legend>

                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiLelongan}"
                                   pagesize="10" cellpadding="0" cellspacing="0" requestURI="/lelong/bayaranbaki"
                                   id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column property="enkuiri.permohonan.idPermohonan" title="ID Permohonan"/>
                        <display:column property="baki" title="Amaun Kena Bayar (RM)" format="{0,number, 0.00}"/>
                        <display:column title="Tarikh Lelongan Awam">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhLelong}"/>
                        </display:column>
                        <display:column title="Tarikh Akhir Bayaran">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhAkhirBayaran}"/>
                        </display:column>
                        <display:column  title="Hari Ke Berapa ?">
                            <s:text name="test${line_rowNum}" id="test${line_rowNum}" value=""/>
                            <script type="text/javascript">kire('${line.tarikhLelong}','${line_rowNum}');</script>
                        </display:column>
                        <display:column title="Status">
                            ${line.baki <= 0 ? "BAYAR" : "BELUM BAYAR" }
                        </display:column>
                    </display:table>
                </div>
                <p align="center">
                    <s:button name="genReport" id="report" value="Surat Bayar Baki" class="longBtn" onclick="showReport();"/>
                </p>
            </fieldset>
        </div>

    </c:if>

   
</s:form>
