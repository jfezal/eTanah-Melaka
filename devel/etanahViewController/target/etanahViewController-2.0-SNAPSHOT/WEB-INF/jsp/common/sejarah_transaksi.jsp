<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>


<script type="text/javascript">

    function edit2(f, id1){
        var queryString = $(f).formSerialize()

    
        window.open("${pageContext.request.contextPath}/common/carian_hakmilik?cetak&"+queryString+"&idHakmilik="+id1, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }
    
    function cetakPemugut(f){
        var form = $(f).formSerialize();
        var negeri = '${actionBean.kodNegeri}';
        var report = null;
        var param1 = null;
        if(negeri == 'melaka'){
            report = 'HSLPenyataCukaiTanah_MLK.rdf';
            param1 = document.getElementById('no_akaun');
        } else {
            report = 'HSLPenyataCukaiTanah.rdf';
            param1 = document.getElementById('id_hakmilik');
        }

        var param = document.getElementById('id_hakmilik');
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?" + form + "&reportName=" + report + "&report_p_id_hakmilik=" + param.value + "&report_p_no_akaun=" + param1.value, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }

</script>

<s:form beanclass="etanah.view.stripes.common.CarianHakmilik">
 <br>
    <c:if test="${actionBean.kodNegeri ne 'melaka'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Perihal Cukai Semasa
                </legend>                               

                    <div class="content" align="center">

                        <display:table class="tablecloth" name="${actionBean.transList}" 
                                       pagesize="10" cellpadding="0" cellspacing="0" 
                                       requestURI="/common/carian_hakmilik?&selectedTab=maklumat_transaksi" excludedParams="selectedTab" id="line3">
                            <display:column title="Bil" sortable="true"> <div align="center">${line3_rowNum}</div></display:column>
                            <display:column  title="Jenis Transaksi" >
                                ${line3.kodTransaksi.kod} - ${line3.kodTransaksi.nama}</display:column>
                            <c:if test="${line3.dokumenKewangan.noRujukanManual ne null}">
                                <display:column  title="No Resit"  ><a href="#" onclick="edit3(this.form, '${line3.dokumenKewangan.idDokumenKewangan}');">${line3.dokumenKewangan.noRujukanManual}&nbsp;</a></display:column>
                            </c:if>
                            <c:if test="${line3.dokumenKewangan.noRujukanManual eq null}">
                                <display:column  title="No Resit"  ><a href="#" onclick="edit3(this.form, '${line3.dokumenKewangan.idDokumenKewangan}');">${line3.dokumenKewangan.idDokumenKewangan}&nbsp;</a></display:column>
                            </c:if>
                            <display:column title="Cara Bayaran" >
                                <c:forEach items="${line3.dokumenKewangan.senaraiBayaran}" var="senarai" >
                                    <c:out value="${senarai.caraBayaran.kodCaraBayaran.nama}" /><br>
                                </c:forEach>
                            </display:column>
                            <display:column title="Bank / Agensi Pembayaran">
                                <c:forEach items="${line3.dokumenKewangan.senaraiBayaran}" var="senarai">
                                    <c:out value="${senarai.caraBayaran.bank.nama}" /><br>
                                </c:forEach>
                            </display:column>
                            <display:column title="No Rujukan">
                                <c:forEach items="${line3.dokumenKewangan.senaraiBayaran}" var="senarai" >
                                    <c:out value="${senarai.caraBayaran.noRujukan}" /><br>
                                </c:forEach>
                            </display:column>
                            <display:column title="Tarikh Transaksi" >
                                <c:choose >
                                    <c:when test="${line3.dokumenKewangan.tarikhTransaksi ne null}">
                                            <fmt:formatDate value="${line3.dokumenKewangan.tarikhTransaksi}" pattern="dd/MM/yyyy hh:mm aa"/>
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:formatDate value="${line3.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm aa"/>
                                    </c:otherwise>
                                </c:choose>
                            </display:column>
                            <display:column property="untukTahun" title="Tahun" />
                            <display:column property="status.nama" title="Status"/>
                            <display:column title="Akaun Debit (RM)" style="text-align:right">
                                <c:if test="${line3.status.kod eq 'A' and line3.kodTransaksi.kod ne '99000'
                                              and line3.kodTransaksi.kod ne '99001' and line3.kodTransaksi.kod ne '99002'
                                              and line3.kodTransaksi.kod ne '99051' and line3.kodTransaksi.kod ne '99050'
                                              and line3.kodTransaksi.kod ne '99003' and line3.kodTransaksi.kod ne '99030'}">
                                    <fmt:formatNumber value="${line3.amaun}" pattern="#,###,##0.00"/>
                                </c:if>
                                <c:if test="${line3.status.kod ne 'A'}">-</c:if>
                            </display:column>
                            <display:column title="Akaun Kredit (RM)" style="text-align:right">
                                <c:if test="${line3.status.kod eq 'A'}">-</c:if>
                                <c:if test="${line3.status.kod ne 'A'}"><fmt:formatNumber value="${line3.amaun}" pattern="0.00"/></c:if>
                               <c:if test="${line3.status.kod eq 'A' and line3.kodTransaksi.kod eq '99002'
                                            or line3.kodTransaksi.kod eq '99000'or line3.kodTransaksi.kod eq '99001'
                                            or line3.kodTransaksi.kod eq '99051'or line3.kodTransaksi.kod eq '99050'
                                            or line3.kodTransaksi.kod eq '99003'or line3.kodTransaksi.kod eq '99030'}">
                                <fmt:formatNumber value="${line3.amaun}" pattern="#,###,##0.00"/>
                              </c:if>
                            </display:column>
                            <display:column title="Dimasuk Oleh" >
                                <c:out value="${line3.infoAudit.dimasukOleh.nama}"/>
                            </display:column>

                        </display:table>
                    </div>
                

            </fieldset>

        </div>
    </c:if>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Sejarah Transaksi</legend>

            <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.sejarahList}" 
                               requestURI="/common/carian_hakmilik?&selectedTab=maklumat_transaksi" 
                               excludedParams="selectedTab" pagesize="10" cellpadding="0" cellspacing="0" id="line2">

                    <display:column title="Bil" sortable="true"> <div align="center">${line2_rowNum}</div></display:column>
                    <display:column  title="Jenis Transaksi" >
                        ${line2.kodTransaksi.kod} - ${line2.kodTransaksi.nama}</display:column>
                    <display:column  title="No Resit"  >
                        <c:if test="${line2.kodTransaksi.kod ne '99008'}">
                            <c:if test="${line2.dokumenKewangan.noRujukanManual ne null}">
                                ${line2.dokumenKewangan.noRujukanManual}&nbsp;
                            </c:if>
                            <c:if test="${line2.dokumenKewangan.noRujukanManual eq null}">
                                ${line2.dokumenKewangan.idDokumenKewangan}&nbsp;
                            </c:if>
                        </c:if>
                    </display:column>
                    <display:column title="Cara Bayaran" >
                        <c:if test="${line2.kodTransaksi.kod ne '99008'}">
                            <c:forEach items="${line2.dokumenKewangan.senaraiBayaran}" var="senarai" >
                                <c:out value="${senarai.caraBayaran.kodCaraBayaran.nama}" /><br>
                            </c:forEach>
                        </c:if>
                    </display:column>

                    <display:column title="Bank / Agensi Pembayaran">
                        <c:forEach items="${line2.dokumenKewangan.senaraiBayaran}" var="senarai">
                            <c:out value="${senarai.caraBayaran.bank.nama}" /><br>
                        </c:forEach>
                    </display:column>
                    <display:column title="No Rujukan">
                        <c:forEach items="${line2.dokumenKewangan.senaraiBayaran}" var="senarai" >
                            <c:out value="${senarai.caraBayaran.noRujukan}" /><br>
                        </c:forEach>
                    </display:column>
                    <%--<display:column property="infoAudit.tarikhMasuk" title="Tarikh Transaksi" class="cawangan${line_rowNum}" format="{0,date,dd/MM/yyyy,hh:mm aa}"/>--%>
                    <display:column title="Tarikh Transaksi" >
                        <c:if test="${line2.dokumenKewangan.noRujukanManual ne null}">
                            <fmt:formatDate value="${line2.dokumenKewangan.tarikhTransaksi}" pattern="dd/MM/yyyy hh:mm aa"/>
                        </c:if>
                        <c:if test="${line2.dokumenKewangan.noRujukanManual eq null}">
                            <fmt:formatDate value="${line2.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm aa"/>
                        </c:if>
                    </display:column>
                    <display:column property="untukTahun" title="Tahun"/>
                    <display:column property="status.nama" title="Status"/>
                    <%-- <display:column property="akaunDebit.baki" title="Akaun Debit (RM)" class="cawangan${line2_rowNum}" format="{0,number, 0.00}" style="text-align:right"/>
                     <display:column property="akaunKredit.baki" title="Akaun Kredit (RM)" class="cawangan${line2_rowNum}" format="{0,number, 0.00}" style="text-align:right"/>--%>
                    <display:column title="Akaun Debit (RM)" style="text-align:right">
                        <c:if test="${line2.status.kod eq 'A'}">
                            <fmt:formatNumber value="${line2.amaun}" pattern="0.00"/>
                        </c:if>
                        <c:if test="${line2.status.kod ne 'A'}">
                            -
                        </c:if>
                    </display:column>
                    <display:column title="Akaun Kredit (RM)" style="text-align:right">
                        <c:if test="${line2.status.kod eq 'A'}">
                            -
                        </c:if>
                        <c:if test="${line2.status.kod ne 'A'}">
                            <fmt:formatNumber value="${line2.amaun}" pattern="0.00"/>
                        </c:if>
                    </display:column>
                    <display:column title="Dimasuk Oleh" >
                        <c:out value="${line2.infoAudit.dimasukOleh.nama}"/>
                    </display:column>
                </display:table>
            </div>
            <div align="right">
               <%--<s:button name=""   onclick="cetakPemugut(this.form, '${actionBean.hakmilik.idHakmilik}');" value="Cetak" class="btn" style="display:${actionBean.btn2}"/>--%>
                           <s:button name=""   onclick="cetakPemugut(this.form);" value="Cetak" class="btn" style="display:${actionBean.btn2}"/>
            </div>
          
        </fieldset>
    </div>
    <div id="sejarah">
    </div>
</s:form>