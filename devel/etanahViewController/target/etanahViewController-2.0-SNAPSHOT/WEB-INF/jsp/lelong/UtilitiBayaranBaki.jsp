<%-- 
    Document   : UtilitiBayaranBaki
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
    
    function viewHakmilik(id){
        window.open("${pageContext.request.contextPath}/lelong/bayaranbaki?viewhakmilikDetail&idHakmilik="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
    }

    function popupReset(idMohon,idLelong, f){
        if(confirm("Anda pasti untuk kemaskini baki kepada RM 0.00 untuk ID Permohonan : "+idMohon+" ?")){
            var url = '${pageContext.request.contextPath}/lelong/bayaranbaki?reset&idPermohonan='+idMohon+'&idLelongan='+idLelong;
            f.action = url;
            f.submit();
        }
    <%--window.open("${pageContext.request.contextPath}/lelong/bayaranbaki?reset", "eTanah",
    "status=0,toolbar=0,location=0,menubar=0,width=1000,height=1200");--%>
        }

        function showReport(){
            window.open("${pageContext.request.contextPath}/lelong/bayaranbaki?cetak", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
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
    <c:if test="${actionBean.flag}">
        <br>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Permohonan
                </legend>

                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiLelongan}"
                                   pagesize="10" cellpadding="0" cellspacing="0" requestURI="/lelong/bayaranbaki"
                                   id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Hakmilik">
                            <a href="#" onclick="viewHakmilik('${line.hakmilikPermohonan.hakmilik.idHakmilik}');return false;">${line.hakmilikPermohonan.hakmilik.idHakmilik}</a>
                        </display:column>
                        <display:column property="baki" title="Amaun Kena Bayar(RM)" format="{0,number, 0.00}"/>
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
                        <display:column title="Bayaran">
                            <s:button class="btn"  onclick="popupReset('${line.enkuiri.permohonan.idPermohonan}','${line.idLelong}', this.form);" name="" value="Pembayaran"/>
                        </display:column>
                    </display:table>
                </div>
                <c:if test="${actionBean.disabled ne null}">
                    <p align="center">
                        <s:button class="btn" onclick="showReport();" name="popup" value="Surat Bayar Baki"/>
                    </p>
                </c:if>
            </fieldset>
        </div>

    </c:if>


</s:form>
