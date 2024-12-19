<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function doViewReport(v) {
    <%--var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
    window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");--%>
        var idmohon = '${actionBean.mohonHakmilik.permohonan.idPermohonan}';
        var report = 'STRJadualPetak_MLK.rdf';
        var url = "reportName=" + report + "%26report_p_id_mohon=" + idmohon;
        //alert(url);
        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }
</script>

<s:form beanclass="etanah.view.strata.JadualPetakExcel">
    <s:messages/>
    <s:errors/>
    <fieldset class="aras1">
        <table>
                      <p align="center">
                          <s:button class="longbtn" name="delete" value="Hapus Jadual Petak"  onclick="doSubmit(this.form,this.name,'page_div')" />
                          <s:button name="" value="Papar Jadual Petak" class="longbtn" onclick="doViewReport('${actionBean.mohonHakmilik.permohonan.idPermohonan}');" />
                      </p>
            <c:if test="${fn:length(actionBean.listBngn) > 0}">
                <%--<c:if test="${actionBean.mohonHakmilik.permohonan.kodUrusan.kod eq 'PFUS' || actionBean.mohonHakmilik.permohonan.kodUrusan.kod eq 'SFUS'
                              || actionBean.mohonHakmilik.permohonan.kodUrusan.kod eq 'PSBS' || actionBean.mohonHakmilik.permohonan.kodUrusan.kod eq 'PBBD'
                              || actionBean.mohonHakmilik.permohonan.kodUrusan.kod eq 'PBBS'}"> 
                      <p align="center">
                          <s:button class="longbtn" name="delete" value="Hapus Jadual Petak"  onclick="doSubmit(this.form,this.name,'page_div')" />
                          <s:button name="" value="Papar Jadual Petak" class="longbtn" onclick="doViewReport('${actionBean.mohonHakmilik.permohonan.idPermohonan}');" />
                      </p>
                <%--</c:if>--%>
                
                Jumlah bangunan : ${fn:length(actionBean.listBngn)}<br />
                Jumlah Tingkat : ${fn:length(actionBean.listTgkt)}<br />
                Jumlah Petak : ${fn:length(actionBean.listPetak)}<br />
                Jumlah Petak Aksesori : ${fn:length(actionBean.listPetakAksesori)}<br />
                Jumlah Pelan Tambahan : ${fn:length(actionBean.listPelan)}<br /><br /><br />
                
                <lagend><font color="blue" ><h4>Ringkasan Bangunan</h4></font></lagend>
                <p>
                    <display:table style="width:80%" class="tablecloth" name="${actionBean.listBngn}"  id="line">
                        <display:column title="Nama Bangunan" property="nama" />
                        <display:column title="Jumlah Syer Bangunan" property="syerBlok" />
                        <display:column title="Kategori Bangunan" property="kodKategoriBangunan.nama" />
                        <display:column title="Kegunaan Bangunan" property="kodKegunaanBangunan.nama" />
                        <display:column title="Bilangan Tingkat" property="bilanganTingkat" />
                        <display:column title="Bilangan Petak" property="bilanganPetak" />
                        <display:column title="Menara" property="lain" />

                        <c:set value="${count +1}" var="count"/>
                    </display:table>
                </p>
            </c:if>

            <c:if test="${fn:length(actionBean.listTgkt) > 0}">
                <lagend><font color="blue" ><h4>Ringkasan Tingkat </h4></font></lagend>
                <p>
                    <display:table style="width:80%" class="tablecloth" name="${actionBean.listTgkt}"  id="line">
                        <display:column title="Tingkat" property="nama" />
                        <display:column title="Bilangan Petak" property="bilanganPetak" />
                        <display:column title="Bilangan Petak Aksesori" property="bilanganPetakAksesori" />
                        <display:column title="Mezanin" property="mezanin" />
                        <display:column title="Menara" property="lain" />

                        <c:set value="${count +1}" var="count"/>
                    </display:table>
                </p>
            </c:if>
            <c:if test="${fn:length(actionBean.listPetak) > 0}">
                <lagend><font color="blue" ><h4>Ringkasan Petak </h4></font></lagend>
                <p>
                    <display:table style="width:80%" class="tablecloth" name="${actionBean.listPetak}"  id="line">
                        <display:column title="Tingkat" property="tingkat.nama" />
                        <display:column title="Petak" property="nama" />
                        <display:column title="Luas">${line.luas} ${line.luasUom.nama}</display:column>
                        <display:column title="Syer" property="syer" />
                        <display:column title="Kegunaan Petak" property="kegunaan.nama" />
                        <display:column title="Pelan Petak" property="pabPetak" />
                        <c:if test="${line.syarat ne null}">
                            <display:column title="Syarat Nyata">${line.syarat.kodSyarat} - ${line.syarat.syarat}</display:column>                       
                        </c:if>
                        <c:if test="${line.sekatan ne null}">
                            <display:column title="Sekatan">${line.sekatan.kodSekatan} - ${line.sekatan.sekatan}</display:column>
                        </c:if>
                        

                        <c:set value="${count +1}" var="count"/>
                    </display:table>
                </p>
            </c:if>
            <c:if test="${fn:length(actionBean.listPetakAksesori) > 0}">
                <lagend><font color="blue" ><h4>Ringkasan Petak Aksesori </h4></font></lagend>
                <p>
                    <display:table style="width:80%" class="tablecloth" name="${actionBean.listPetakAksesori}"  id="line">
                        <display:column title="Petak" property="petak.nama" />
                        <display:column title="Nama Petak Aksesori" property="nama" />
                        <display:column title="Kegunaan Petak Aksesori" property="kegunaan.nama" />
                        <display:column title="Lokasi" property="lokasi" />
                        <display:column title="Luas">${line.lain} Meter Persegi</display:column>
                        <display:column title="Pelan" property="pabAksesori" />

                        <c:set value="${count +1}" var="count"/>
                    </display:table>
                </p>
            </c:if>
            <c:if test="${fn:length(actionBean.listPelan) > 0}">
                <lagend><font color="blue" ><h4>Senarai Pelan Tambahan </h4></font></lagend>
                <p>
                    <display:table style="width:80%" class="tablecloth" name="${actionBean.listPelan}"  id="line">
                        <display:column title="Bil" >${line_rowNum}</display:column>
                        <display:column title="No Pelan" property="pabAksesori" />
                        <c:set value="${count +1}" var="count"/>
                    </display:table>
                </p>
            </c:if>
        </table>
    </fieldset>
</s:form>

