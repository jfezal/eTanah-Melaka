<%-- 
    Document   : pertanyaan_cukai_2
    Created on : Nov 17, 2009, 12:18:48 PM
    Author     : nurfaizati
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
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>



<script type="text/javascript">
 
    function edit1(f, id1){
        var queryString = $(f).formSerialize()

        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?cetak&"+queryString+"&idHakmilik="+id1, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }
</script>

<script type="text/javascript">

    function edit(f, id1){
        var queryString = $(f).formSerialize()
     
        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?hakmilikDetail&"+queryString+"&hakmilik.idHakmilik="+id1, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1035,height=200");
    }


    function edit2(f, id1 ,id2){
        var queryString = $(f).formSerialize()

        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?pembayarDetail&"+queryString+"&idPihak="+id1+"&idHakmilik="+id2, "eTanah",
        "status=0,toolbar=0,resizable=1,scrollbars=1,location=0,menubar=0,width=1035,height=420");
    }

    function refreshPage1(f){
        var queryString = $(f).formSerialize()
        var url = "${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?refreshpage&"+queryString;
        $.get(url,
        function(data){
            $('#aa').html(data);
        },'html');
            
    }
        
</script>
<div id="aa">
    <div class="subtitle">
        <s:form beanclass="etanah.view.stripes.hasil.PertanyaanHakmilikActionBean">
            <table width="100%" bgcolor="green">
                <tr>
                    <td width="100%" height="20" >
                        <div style="background-color: green;" align="center">
                            <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pertanyaan Hakmilik</font>
                        </div>
                    </td>
                </tr>
            </table>

            <%--<s:hidden name="noAkaun"/>--%>
            <s:hidden name="hakmilik.idHakmilik"/>
            <s:hidden name="akaun.noAkaun"/>

            <fieldset class="aras1">
                <legend>Hakmilik </legend>

                <p>
                    <label>ID Hakmilik :</label>
                    ${actionBean.hakmilik.idHakmilik}&nbsp;
                </p>
                 <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                  <p>
                    <label>No Akaun:</label>
                    ${actionBean.akaun.noAkaun}&nbsp;
                </p>
                 </c:if>

              </fieldset>
            <br>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Hakmilik
                    </legend>

                    <p>
                        <label>Daerah :</label>
                        ${actionBean.hakmilik.daerah.nama}&nbsp;
                    </p>

                    <p>
                        <label>Bandar/Pekan/Mukim :</label>
                        ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;
                    </p>
                    <p>
                        <label>Jenis Hakmilik :</label>
                        ${actionBean.hakmilik.kodHakmilik.nama}&nbsp;
                    </p>

                    <p>
                        <label>Jenis Penggunaan Tanah :</label>
                        ${actionBean.hakmilik.kategoriTanah.nama}&nbsp;
                    </p>

                    <p>
                        <label>Kod Lot :</label>
                        ${actionBean.hakmilik.lot.nama}&nbsp;

                    </p>
                    <p>
                        <label>No Lot/PT :</label>
                        ${actionBean.hakmilik.noLot}&nbsp;

                    </p>

                    <p>
                        <label >Syarat Nyata :</label>
                        <a href="#" onclick="edit(this.form, '${actionBean.hakmilik.idHakmilik}');">${actionBean.hakmilik.syaratNyata.kod}&nbsp;</a>
                    </p>

                    <p>
                        <label>No Pelan Piawai :</label>
                        ${actionBean.hakmilik.noLitho}&nbsp;
                    </p>
                    <p>
                        <label>No Pelan Akui :</label>
                        ${actionBean.hakmilik.noPelan}&nbsp;
                    </p>
                    <p>
                        <label >Keluasan  :</label>
                        <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp; ${actionBean.hakmilik.kodUnitLuas.nama}
                    </p>
                    <p>
                        <label >Cukai Tanah Tahunan (RM)  :</label>

                        <fmt:formatNumber value="${actionBean.hakmilik.cukaiSebenar}" pattern="0.00"/>&nbsp;
                    </p>
                    <p>
                        <label>Status Hakmilik:</label>
                        ${actionBean.hakmilik.kodStatusHakmilik.nama}&nbsp;
                    </p>

                </fieldset>
            </div>
            <br>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Tuan Tanah
                    </legend>

                    <div class="content" align="center">

                        <display:table class="tablecloth" name="${actionBean.pihakList}" pagesize="4" cellpadding="0" cellspacing="0" requestURI="/hasil/pertanyaan_hakmilik_melaka" id="line">
                            <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                            <display:column property="pihak.nama" title="Nama" class="cawangan${line_rowNum}" />
                            <display:column property="pihak.noPengenalan" title="No Pengenalan" class="cawangan${line_rowNum}" />
                            <display:column title="Alamat" class="alamat">
                                ${line.pihak.suratAlamat1}<c:if test="${line.pihak.suratAlamat2 ne null}">,</c:if>
                                ${line.pihak.suratAlamat2}<c:if test="${line.pihak.suratAlamat3 ne null}">,</c:if>
                                ${line.pihak.suratAlamat3}<c:if test="${line.pihak.suratAlamat4 ne null}">,</c:if>
                                ${line.pihak.suratAlamat4}<c:if test="${line.pihak.suratPoskod ne null}">,</c:if>
                                ${line.pihak.suratPoskod}<c:if test="${line.pihak.suratNegeri.nama ne null}">,</c:if>
                                ${line.pihak.suratNegeri.nama}
                            </display:column>
                        </display:table>
                    </div>

                </fieldset>
            </div>

            <br>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Pembayar
                    </legend>

                    <p><label>Nama :</label>
                        <a href="#" onclick="edit2(this.form, '${actionBean.pihak.idPihak}','${actionBean.hakmilik.idHakmilik}');">${actionBean.pihak.nama}&nbsp;</a>&nbsp;
                    </p>
                    <p>
                        <label>Alamat Tetap :</label>
                        ${actionBean.pihak.alamat1}
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.pihak.alamat2}
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.pihak.alamat3}
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.pihak.alamat4}&nbsp;
                    </p>

                    <p>
                        <label>Poskod :</label>
                        ${actionBean.pihak.poskod}&nbsp;
                    </p>
                    <p>
                        <label>Negeri :</label>
                        ${actionBean.pihak.negeri.nama}&nbsp;

                    </p>
                    <p>
                        <label>Alamat Surat Menyurat :</label>
                        ${actionBean.pihak.suratAlamat1}
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.pihak.suratAlamat2}
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.pihak.suratAlamat3}
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.pihak.suratAlamat4}&nbsp;
                    </p>

                    <p>
                        <label>Poskod :</label>
                        ${actionBean.pihak.suratPoskod}&nbsp;
                    </p>
                    <p>
                        <label>Negeri :</label>
                        ${actionBean.pihak.suratNegeri.nama}&nbsp;

                    </p>
                    <p>
                        <label>Nombor Telefon :</label>
                        ${actionBean.pihak.noTelefon1}&nbsp;

                    </p>
                    <p>
                        <label>Nombor Telefon Bimbit:</label>
                        ${actionBean.pihak.noTelefonBimbit}&nbsp;
                    </p>
                    <p>
                        <label>Email:</label>
                        ${actionBean.pihak.email}
                    </p>

                </fieldset>
            </div>
            <br>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Perihal Cukai
                    </legend>

                    <div class="content" align="center">
                      
                        <display:table class="tablecloth" name="${actionBean.transList}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/hasil/pertanyaan_hakmilik" id="line">
                          
                             <c:if test="${line.dokumenKewangan.status.kod eq 'B'}">
                            <display:column title="Bil" sortable="true" style="text-decoration:line-through;"> <div align="center">${line_rowNum}</div></display:column>
                             </c:if>
                             <c:if test="${line.dokumenKewangan.status.kod ne 'B' or line.dokumenKewangan.idDokumenKewangan eq null }">
                            <display:column title="Bil" sortable="true" > <div align="center">${line_rowNum}</div></display:column>
                             </c:if>
                         
                            <c:if test="${line.dokumenKewangan.status.kod eq 'B'}">
                                <display:column  title="Jenis Transaksi" style="text-decoration:line-through;">${line.kodTransaksi.kod} - ${line.kodTransaksi.nama} </display:column>
                            </c:if>
                            <c:if test="${line.dokumenKewangan.status.kod ne 'B' or line.dokumenKewangan.idDokumenKewangan eq null}">
                                <display:column  title="Jenis Transaksi">${line.kodTransaksi.kod} - ${line.kodTransaksi.nama} </display:column>
                            </c:if>
                        
                            <c:if test="${line.dokumenKewangan.status.kod eq 'B'}">
                                <display:column  title="No Resit" style="text-decoration:line-through;">${line.dokumenKewangan.idDokumenKewangan} </display:column>
                            </c:if>
                          
                                <c:if test="${line.dokumenKewangan.status.kod ne 'B' or line.dokumenKewangan.idDokumenKewangan eq null}">
                                    <display:column  title="No Resit" >${line.dokumenKewangan.idDokumenKewangan} </display:column>
                                </c:if>
                       
                            <c:if test="${line.dokumenKewangan.status.kod eq 'B'}">
                                <display:column title="Cara Bayaran" style="text-decoration:line-through;" >
                                    <c:forEach items="${line.dokumenKewangan.senaraiBayaran}" var="senarai" >
                                        <c:out value="${senarai.caraBayaran.kodCaraBayaran.nama}" /><br>
                                    </c:forEach>
                                </display:column>
                            </c:if>
                            <c:if test="${line.dokumenKewangan.status.kod ne 'B' or line.dokumenKewangan.idDokumenKewangan eq null}">
                                <display:column title="Cara Bayaran"  >
                                    <c:forEach items="${line.dokumenKewangan.senaraiBayaran}" var="senarai" >
                                        <c:out value="${senarai.caraBayaran.kodCaraBayaran.nama}" /><br>
                                    </c:forEach>
                                </display:column>
                            </c:if>
                            <c:if test="${line.dokumenKewangan.status.kod eq 'B'}">
                                <display:column title="Bank / Agensi Pembayaran"style="text-decoration:line-through;" >
                                    <c:forEach items="${line.dokumenKewangan.senaraiBayaran}" var="senarai">
                                        <c:out value="${senarai.caraBayaran.bank.nama}" /><br>
                                    </c:forEach>
                                </display:column>
                            </c:if>
                            <c:if test="${line.dokumenKewangan.status.kod ne 'B' or line.dokumenKewangan.idDokumenKewangan eq null}">
                                <display:column title="Bank / Agensi Pembayaran" >
                                    <c:forEach items="${line.dokumenKewangan.senaraiBayaran}" var="senarai">
                                        <c:out value="${senarai.caraBayaran.bank.nama}" /><br>
                                    </c:forEach>
                                </display:column>
                            </c:if>
                            <c:if test="${line.dokumenKewangan.status.kod eq 'B'}">
                                <display:column title="No Rujukan" style="text-decoration:line-through;">
                                    <c:forEach items="${line.dokumenKewangan.senaraiBayaran}" var="senarai" >
                                        <c:out value="${senarai.caraBayaran.noRujukan}" /><br>
                                    </c:forEach>
                                </display:column>
                            </c:if>
                            <c:if test="${line.dokumenKewangan.status.kod ne 'B' or line.dokumenKewangan.idDokumenKewangan eq null}">
                                <display:column title="No Rujukan" >
                                    <c:forEach items="${line.dokumenKewangan.senaraiBayaran}" var="senarai" >
                                        <c:out value="${senarai.caraBayaran.noRujukan}" /><br>
                                    </c:forEach>
                                </display:column>
                            </c:if>
                            <c:if test="${line.dokumenKewangan.status.kod eq 'B'}">
                                <display:column title="Tarikh Transaksi" style="text-decoration:line-through;">
                                    <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm aa"/>
                                </display:column>
                            </c:if>
                            <c:if test="${line.dokumenKewangan.status.kod ne 'B' or line.dokumenKewangan.idDokumenKewangan eq null}">
                                <display:column title="Tarikh Transaksi" >
                                    <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm aa"/>
                                </display:column>
                            </c:if>
                            <c:if test="${line.dokumenKewangan.status.kod eq 'B'}">
                                <display:column property="untukTahun" title="Tahun" style="text-decoration:line-through;"/>
                            </c:if>
                            <c:if test="${line.dokumenKewangan.status.kod ne 'B' or line.dokumenKewangan.idDokumenKewangan eq null}">
                                <display:column property="untukTahun" title="Tahun" />
                            </c:if>
                             <c:if test="${line.dokumenKewangan.status.kod eq 'B'}">
                            <display:column property="status.nama" title="Status" style="text-decoration:line-through;"/>
                             </c:if>
                             <c:if test="${line.dokumenKewangan.status.kod ne 'B' or line.dokumenKewangan.idDokumenKewangan eq null}">
                            <display:column property="status.nama" title="Status" />
                             </c:if>
                            <c:if test="${line.dokumenKewangan.status.kod eq 'B'}">
                            <display:column property="amaun" title="Amaun (RM)" class="cawangan${line_rowNum}" format="{0,number, 0.00}" style="text-align:right;text-decoration:line-through; " />
                            </c:if>
                            <c:if test="${line.dokumenKewangan.status.kod ne 'B' or line.dokumenKewangan.idDokumenKewangan eq null}">
                            <display:column property="amaun" title="Amaun (RM)" class="cawangan${line_rowNum}" format="{0,number, 0.00}" style="text-align:right " />
                            </c:if>
                         <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                            <display:footer>
                                <tr>
                                    <td colspan="9" align="right"><div align="right"><b>Jumlah (RM) : </b></div></td>
                                    <td class="number" align="right"><div align="right"><fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/></div></td>
                                </tr>
                            </display:footer>
                        </c:if>
                            <display:footer>
                                <tr>
                                    <td colspan="8" align="right"><div align="right"><b>Jumlah (RM) : </b></div></td>
                                    <td class="number" align="right"><div align="right"><fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/></div></td>
                                </tr>
                            </display:footer>
                           </display:table>
                    </div>

                </fieldset>

            </div>
            <br>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Denda & Notis
                    </legend>
                    <div align="center">
                        <table align="center">
                            <tr>
                                <td width="70"><font color="#003194" size="2px" style="Tahoma"><b>Jumlah </b></font></td>
                                <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                                <td><font size="2">RM<fmt:formatNumber value="${actionBean.jum}" pattern="0.00"/></font></td>
                                <td width="60">&nbsp;</td>
                                <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Sebelum 1 Jun </b></font></td>
                                <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                                <td><font size="2">RM<fmt:formatNumber value="${actionBean.jum}" pattern="0.00"/></font></td>
                            </tr>
                            <tr>
                                <td width="70"><font color="#003194" size="2px" style="Tahoma"><b>Denda</b></font></td>
                                <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                                <td><font size="2">RM<fmt:formatNumber value="${actionBean.denda}" pattern="0.00"/></font></td>
                                <td width="60">&nbsp;</td>
                                <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Selepas 31 Mei </b></font></td>
                                <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                                <td><font size="2">RM<fmt:formatNumber value="${actionBean.jumDenda}" pattern="0.00"/></font></td>
                            </tr>
                            <tr>
                                <td width="70"><font color="#003194" size="2px" style="Tahoma"><b>Notis 6A </b></font></td>
                                <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                                <td><font size="2">RM<fmt:formatNumber value="${actionBean.z}" pattern="0.00"/></font></td>
                                <td width="60">&nbsp;</td>
                                <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Jika Notis 6A disampaikan </b></font></td>
                                <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                                <td><font size="2">RM<fmt:formatNumber value="${actionBean.notis}" pattern="0.00"/></font></td>
                            </tr>
                        </table>
                    </div>


                </fieldset>
                <table border="0" bgcolor="green" width="100%">
                    <tr>
                        <td align="right">
                            <s:submit name="kembali" value="Kembali" class="btn" style="display:${actionBean.btn2}"/>
                            <s:button name="cetak" onclick="edit1(this.form, '${line.akaunKredit.hakmilik.idHakmilik}');" value="Cetak" class="btn" style="display:${actionBean.btn2}"/>
                        </td>
                    </tr>
                </table>


            </s:form>
        </div>
    </div>
</div>



