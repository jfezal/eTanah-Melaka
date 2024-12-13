<%-- 
    Document   : maklumat_strata
    Created on : Oct 8, 2012, 2:56:32 PM
    Author     : mazurahayati.yusop
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<s:form beanclass="etanah.view.strata.PertanyaanHakmilikStrataActionBean">
    <div id="bayar">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Strata</legend>
                <p>
                    <label>No Bangunan :</label>
                    <c:choose>
                        <c:when test="${actionBean.hakmilik.kodKategoriBangunan.kod ne 'L'}">
                            ${actionBean.hakmilik.noBangunan}
                        </c:when>
                        <c:otherwise>
                            <s:text name=" " readonly="true"/>
                        </c:otherwise>
                    </c:choose>
                </p>
                <p>
                    <label>No Tingkat :</label>
                    <c:choose>
                        <c:when test="${actionBean.hakmilik.kodKategoriBangunan.kod eq 'B'}">
                            ${actionBean.hakmilik.noTingkat}
                        </c:when>
                        <c:otherwise>
                             TIADA
                        </c:otherwise>
                    </c:choose>

                </p>
                <p>
                    <label>No Petak :</label>
                    <c:choose>
                        <c:when test="${actionBean.hakmilik.kodKategoriBangunan.kod ne 'P'}">
                             ${actionBean.hakmilik.noPetak}
                        </c:when>
                        <c:otherwise>
                            TIADA
                        </c:otherwise>
                    </c:choose>

                </p>

                <center><div id="checkLotMessages" style="font-size: 12px;display: none;margin-top: 10px;margin-bottom: 10px;width:50%;"></div>
                    <div id="checkPelanMessages" style="font-size: 12px;display: none;margin-top: 10px;margin-bottom: 10px;width:50%;"></div></center>

                <p>
                    <label>Petak Aksesori :</label>
                    <%--<s:text name="petakAksesori" readonly="true"/>--%>
                    <%--<s:textarea name="petakAksesori" rows="3" cols="50" readonly="true"/>--%>
                    ${actionBean.petakAksesori}
                </p>
                <p>
                    <label>Jumlah Luas Petak Aksesori :</label>
                    ${actionBean.a} &nbsp; Meter Persegi
                </p>
                <p>
                    <label>Unit Syer bagi Petak :</label>
                    <%--<s:text name="hakmilik.unitSyer" readonly="true"/>--%>
                     ${actionBean.hakmilik.unitSyer}
                </p> 
                <p>
                    <label>Jumlah Unit Syer :</label>
                     ${actionBean.hakmilik.jumlahUnitSyer}
                </p>
                
                <p>
                    <label>No PA(B) :</label>
                    <%--<s:textarea name="petakPelan" rows="3" cols="50" id="petakPelan" readonly="true"/>--%>
                     ${actionBean.petakPelan}
                </p>
                <p>
                    <label>Luas Petak :</label>
                    <%--<s:text name="hakmilik.luas" readonly="true" />--%>
                    ${actionBean.hakmilik.luas}
                    &nbsp; ${actionBean.hakmilik.kodUnitLuas.nama}
                </p>
<!--                <p>
                    <label>Unit Syer :</label>
                    <c:if test ="${actionBean.btn2 ne null}">${actionBean.btn2}</c:if>
                    <c:if test ="${actionBean.btn2 eq null}">${actionBean.hakmilik.unitSyer}</c:if>
                </p>-->
                <p>
                    <label>Tarikh Daftar :</label>
                    <%--<s:text name="hakmilik.tarikhDaftar" readonly="true" formatPattern="dd/MM/yyyy"/>--%>
                    <fmt:formatDate type="date"
                                    pattern="dd/MM/yyyy"
                                    value="${actionBean.hakmilik.tarikhDaftar}"/>
                </p>
                <p>
                    <label>Tarikh Batal :</label>
                    <%--<s:text name="hakmilik.tarikhBatal" readonly="true" formatPattern="dd/MM/yyyy"/>--%>
                    
                    <c:choose>
                        <c:when test="${actionBean.hakmilik.tarikhBatal ne null}">
                             <fmt:formatDate type="date"
                                    pattern="dd/MM/yyyy"
                                    value="${actionBean.hakmilik.tarikhBatal}"/>
                        </c:when>
                        <c:otherwise>
                            TIADA 
                       </c:otherwise>
                    </c:choose>
                </p>
                <p>
                    <label>No Versi Borang 2K :</label>
                    <c:if test="${actionBean.hakmilik.noVersiIndeksStrata eq null}">0 &nbsp;</c:if>
                    <c:if test="${actionBean.hakmilik.noVersiIndeksStrata ne null}">
                    ${actionBean.hakmilik.noVersiIndeksStrata}&nbsp;
                    </c:if>
                </p>
                <p>
                    <label>No Versi Borang 3K :</label>
                    <c:if test="${actionBean.hakmilik.version eq null}">0 &nbsp;</c:if>
                    <c:if test="${actionBean.hakmilik.version ne null}">
                    ${actionBean.hakmilik.version}&nbsp;
                    </c:if>
                </p>
                <p>
                    <label>No Versi DHDE (Borang 4K-Geran) :</label>
                    ${actionBean.hakmilik.noVersiDhde}&nbsp;
                </p>
                <p>
                    <label>No Versi DHKE (Borang 4K-Geran) :</label>
                    ${actionBean.hakmilik.noVersiDhke}&nbsp;
                </p>
                <p>
                    <label>Cukai Asal (RM) :</label>
                    <fmt:formatNumber value="${actionBean.hakmilik.cukai}" pattern="0.00"/>&nbsp;
                </p>
                <p>
                    <label>Cukai Petak Tahunan (RM) :</label>
                    <fmt:formatNumber value="${actionBean.hakmilik.cukaiSebenar}" pattern="0.00"/>&nbsp;
                </p>
                <%--<p>
                    <label>No Versi DHKE :</label>
                    <s:text name="hakmilik.noVersiDhke" readonly="true" />
                </p>
                <p>
                    <label>No Versi DHDE :</label>
                    <s:text name="hakmilik.noVersiDhde" readonly="true" />
                </p>--%>
                <c:if test="${actionBean.hakmilik.noVersiDhde ne 0}">
                    <p>
                        <label>Tarikh Tukar Ganti :</label>
                        ${actionBean.tarikhtkrgnti}&nbsp;
                        <%--<s:text name="hakmilik.infoAudit.tarikhKemaskini" readonly="true"  formatPattern="dd/MM/yyyy"/>--%>
                    </p>
                    <p>
                        <label>Pegawai Tukar Ganti :</label>
                        <c:if test="${actionBean.hmtkrGnti ne null}">
                            ${actionBean.hmtkrGnti.infoAudit.dimasukOleh.nama}&nbsp;
                        </c:if>
                        <c:if test="${actionBean.hmtkrGnti eq null}">
                             ${actionBean.listDokumen[0].infoAudit.dimasukOleh.nama}&nbsp;
                        </c:if>
                        <%--<s:text name="hakmilik.infoAudit.dikemaskiniOleh.nama" size="40" readonly="true" />--%>
                    </p>
                </c:if>
                <br>
            </fieldset>
        </div>
                <div class="subtitle">
      <fieldset class="aras1">
        <legend>
          Maklumat Pembayar
        </legend>
        <p><label>Nama :</label>
          ${actionBean.pihak.nama}&nbsp;
          <%--                    <a href="#" onclick="editMaklumat(this.form, '${actionBean.pihak.idPihak}','${actionBean.hakmilik.idHakmilik}','${actionBean.akaun.noAkaun}');">${actionBean.pihak.nama}&nbsp;</a>&nbsp;
          --%>
          <c:if test="${actionBean.pengguna.perananUtama.kod eq '2' or actionBean.pengguna.perananUtama.kod eq '5' or actionBean.pengguna.perananUtama.kod eq '116' or actionBean.pengguna.perananUtama.kod eq '58'}">
            <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00' or actionBean.pengguna.kodCawangan.kod eq '04' or actionBean.pengguna.kodCawangan.kod eq actionBean.hakmilik.daerah.kod}">
              <a onmouseover="this.style.cursor = 'pointer';" onclick="tambahPembayar(this.form, '${actionBean.hakmilik.idHakmilik}', '${actionBean.akaun.noAkaun}');">
                <img alt="Sila Klik Untuk Pilih Nama Pembayar Yang Baru" src='${pageContext.request.contextPath}/pub/images/edit.gif' />&nbsp;
              </a>&nbsp;&nbsp;
              <a onmouseover="this.style.cursor = 'pointer';" onclick="pihakBaru(this.form, '${actionBean.hakmilik.idHakmilik}', '${actionBean.akaun.noAkaun}');">
                <img alt="Sila Klik Untuk Tambah Pembayar Yang Baru" src='${pageContext.request.contextPath}/pub/images/tambah.png' />&nbsp;
              </a>&nbsp;
            </c:if>
          </c:if>
        </p>
        <p>
          <label>No Pengenalan :</label>
          ${actionBean.pihak.noPengenalan}&nbsp;
        </p>
        <p>
          <label>Alamat Tetap :</label>
          ${actionBean.pihak.alamat1}&nbsp;
        </p>
        <c:if test="${actionBean.pihak.alamat2 ne null}">
          <p>
            <label> &nbsp;</label>
            ${actionBean.pihak.alamat2}&nbsp;
          </p>
        </c:if>
        <c:if test="${actionBean.pihak.alamat3 ne null}">
          <p>
            <label> &nbsp;</label>
            ${actionBean.pihak.alamat3}&nbsp;
          </p>
        </c:if>
        <p>
          <label>Bandar :</label>
          <c:if test="${actionBean.pihak.alamat4 ne null}">
            ${actionBean.pihak.alamat4}&nbsp;
          </c:if>
          <c:if test="${actionBean.pihak.alamat4 eq null}">
            -
          </c:if>
        </p>
        <p>
          <label>Poskod :</label>
          <c:if test="${actionBean.pihak.poskod ne null}">
            ${actionBean.pihak.poskod}&nbsp;
          </c:if>
          <c:if test="${actionBean.pihak.poskod eq null}">
            -
          </c:if>
        </p>
        <p>
          <label>Negeri :</label>
          <c:if test="${actionBean.pihak.negeri.nama ne null}">
            ${actionBean.pihak.negeri.nama}&nbsp;
          </c:if>
          <c:if test="${actionBean.pihak.negeri.nama eq null}">
            -
          </c:if>
        </p>
        <br>
        <p>
          <label>Alamat Surat Menyurat :</label>
          <c:if test="${actionBean.pihak.suratAlamat1 ne null}">
            ${actionBean.pihak.suratAlamat1}&nbsp;
          </c:if>
          <c:if test="${actionBean.pihak.suratAlamat1 eq null}">
            -
          </c:if>


          <c:if test="${actionBean.pengguna.perananUtama.kod eq '2' or actionBean.pengguna.perananUtama.kod eq '5'  or actionBean.pengguna.perananUtama.kod eq '116'}">
            <%--<c:if test="${actionBean.pengguna.kodCawangan.kod eq '00' or actionBean.pengguna.kodCawangan.kod eq actionBean.hakmilik.daerah.kod}">--%>
              <a id="" onclick="editMaklumat(this.form, '${actionBean.pihak.idPihak}', '${actionBean.hakmilik.idHakmilik}', '${actionBean.akaun.noAkaun}');" onmouseover="this.style.cursor = 'pointer';">
                <img alt="Sila Klik Untuk Kemaskini Alamat Pembayar" src='${pageContext.request.contextPath}/pub/images/edit.gif' />
              </a>&nbsp;
            <%--</c:if>--%>
          </c:if>
        </p>
        <c:if test="${actionBean.pihak.suratAlamat2 ne null}">
          <p>
            <label> &nbsp;</label>
            ${actionBean.pihak.suratAlamat2}&nbsp;
          </p>
        </c:if>
        <c:if test="${actionBean.pihak.suratAlamat3 ne null}">
          <p>
            <label> &nbsp;</label>
            ${actionBean.pihak.suratAlamat3}&nbsp;
          </p>
        </c:if>
        <p>
          <label>Bandar :</label>
          <c:if test="${actionBean.pihak.suratAlamat4 ne null}">
            ${actionBean.pihak.suratAlamat4}&nbsp;
          </c:if>
          <c:if test="${actionBean.pihak.suratAlamat4 eq null}">
            -
          </c:if>
        </p>
        <p>
          <label>Poskod :</label>
          <c:if test="${actionBean.pihak.suratPoskod ne null}">
            ${actionBean.pihak.suratPoskod}&nbsp;
          </c:if>
          <c:if test="${actionBean.pihak.suratPoskod eq null}">
            -
          </c:if>
        </p>
        <p>
          <label>Negeri :</label>
          <c:if test="${actionBean.pihak.suratNegeri.nama ne null}">
            ${actionBean.pihak.suratNegeri.nama}&nbsp;
          </c:if>
          <c:if test="${actionBean.pihak.suratNegeri.nama eq null}">
            -
          </c:if>
        </p>
        <br>
        <p>
          <label>Nombor Telefon :</label>
          <c:if test="${actionBean.pihak.noTelefon1 ne null}">
            ${actionBean.pihak.noTelefon1}&nbsp;
          </c:if>
          <c:if test="${actionBean.pihak.noTelefon1 eq null}">
            - &nbsp;
          </c:if>
        </p>
        <p>
          <label>Nombor Telefon Bimbit :</label>
          <c:if test="${actionBean.pihak.noTelefonBimbit ne null}">
            ${actionBean.pihak.noTelefonBimbit}&nbsp;
          </c:if>
          <c:if test="${actionBean.pihak.noTelefonBimbit eq null}">
            - &nbsp;
          </c:if>
        </p>
        <p>
          <label>Email :</label>
          <c:if test="${actionBean.pihak.email ne null}">
            ${actionBean.pihak.email}&nbsp;
          </c:if>
          <c:if test="${actionBean.pihak.email eq null}">
            - &nbsp;
          </c:if>
        </p>
        <br>
      </fieldset>
    </div>
    <br>

                <div class="subtitle">
      <fieldset class="aras1">
        <legend>
          Perihal Cukai Semasa
        </legend>
        <c:if test="${actionBean.kodNegeri ne 'melaka'}">
          <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.transList}"
                           pagesize="10" cellpadding="0" cellspacing="0"
                           requestURI="/common/carian_hakmilik" id="line3">
              <%--<display:table class="tablecloth"  pagesize="10" cellpadding="0" cellspacing="0" requestURI="/common/carian_hakmilik" id="line2">--%>
              <display:column title="Tarikh Transaksi" >
                <c:if test="${line3.dokumenKewangan.noRujukanManual ne null}">
                  <fmt:formatDate value="${line3.dokumenKewangan.tarikhTransaksi}" pattern="dd/MM/yyyy hh:mm aa"/>
                </c:if>
                <c:if test="${line3.dokumenKewangan.noRujukanManual eq null}">
                  <fmt:formatDate value="${line3.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm aa"/>
                </c:if>
              </display:column>
              <display:column  title="Transaksi Penyata">${line3.kodTransaksi.nama}</display:column>
              <display:column  title="No Resit"  >
                <c:if test="${line3.dokumenKewangan.noRujukanManual ne null}"><%-- or line2.dokumenKewangan.noRujukanManual ne ''}">--%>
                  <a href="#" onclick="edit3(this.form, '${line3.dokumenKewangan.idDokumenKewangan}');">
                    ${line3.dokumenKewangan.noRujukanManual}&nbsp;
                  </a>
                </c:if>
                <c:if test="${line3.dokumenKewangan.noRujukanManual eq null}"><%-- or line2.dokumenKewangan.noRujukanManual eq ''}">--%>
                  <c:choose>
                    <c:when test="${line3.dokumenKewangan.noRujukan ne null}">
                      <a href="#" onclick="edit3(this.form, '${line3.dokumenKewangan.idDokumenKewangan}');">
                        ${line3.dokumenKewangan.noRujukan}&nbsp;
                      </a>
                    </c:when>
                    <c:otherwise>
                      <a href="#" onclick="edit3(this.form, '${line3.dokumenKewangan.idDokumenKewangan}');">
                        ${line3.dokumenKewangan.idDokumenKewangan}&nbsp;
                      </a>
                    </c:otherwise>
                  </c:choose>
                </c:if>
              </display:column>
              <display:column property="untukTahun" title="Tahun" />
              <display:column title="Akaun Debit (RM)" style="text-align:right">
                <c:if test="${line3.status.kod eq 'A' and line3.kodTransaksi.kod ne '99000'
                              and line3.kodTransaksi.kod ne '99001'
                              and line3.kodTransaksi.kod ne '99002'
                              and line3.kodTransaksi.kod ne '99003'
                              and line3.kodTransaksi.kod ne '99030' }">
                  <fmt:formatNumber value="${line3.amaun}" pattern="#,##0.00"/>
                </c:if>
                <%--<c:if test="${line2.status.kod eq 'B'}">
                    <fmt:formatNumber value="${line2.amaun}" pattern="#,##0.00"/>
                </c:if>--%>
              </display:column>
              <display:column title="Akaun Kredit (RM)" style="text-align:right">
                <c:if test="${line3.status.kod ne 'A'}"><%-- and line2.status.kod ne 'B'}">--%>
                  <fmt:formatNumber value="${line3.amaun}" pattern="#,##0.00"/>
                </c:if>
                <c:if test="${line3.status.kod eq 'A'
                              and line3.kodTransaksi.kod eq '99002'
                              or line3.kodTransaksi.kod eq '99000'
                              or line3.kodTransaksi.kod eq '99001'
                              or line3.kodTransaksi.kod eq '99003'
                              or line3.kodTransaksi.kod eq '99030'}">
                  <fmt:formatNumber value="${line3.amaun}" pattern="#,##0.00"/>
                </c:if>
              </display:column>
              <display:column property="status.nama" title="Status"/>
              <display:column title="Tarikh Batal" >
                <c:if test="${line3.status.kod eq 'B'}">
                  <fmt:formatDate value="${line3.dokumenKewangan.tarikhBatal}" pattern="dd/MM/yyyy hh:mm aa"/>
                </c:if>
              </display:column>
              <%-- <display:column title="Dimasuk Oleh" >
                   <c:out value="${line2.infoAudit.dimasukOleh.nama}"/>
               </display:column>--%>
            </display:table>
          </div>
        </c:if>

        <c:if test="${actionBean.kodNegeri eq 'melaka'}">
          <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.transList}" pagesize="10" cellpadding="0"
                           cellspacing="0" requestURI="/common/carian_hakmilik" id="line3">
              <display:column title="Bil" sortable="true"> <div align="center">${line3_rowNum}</div></display:column>
              <display:column title="Tarikh Transaksi" >
                <c:if test="${line3.dokumenKewangan.noRujukanManual ne null}">
                  <fmt:formatDate value="${line3.dokumenKewangan.tarikhTransaksi}" pattern="dd/MM/yyyy hh:mm aa"/>
                </c:if>
                <c:if test="${line3.dokumenKewangan.noRujukanManual eq null}">
                  <fmt:formatDate value="${line3.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm aa"/>
                </c:if>
              </display:column>
              <display:column  title="Jenis Transaksi" >
                ${line3.kodTransaksi.kod} - ${line3.kodTransaksi.nama}</display:column>
              <c:if test="${line3.dokumenKewangan.noRujukanManual ne null}"><%-- or line2.dokumenKewangan.noRujukanManual ne ''}">--%>
                <display:column  title="No Resit"  >
                  <a href="#" onclick="edit3(this.form, '${line3.dokumenKewangan.idDokumenKewangan}');">
                    ${line3.dokumenKewangan.noRujukanManual}&nbsp;
                  </a>
                </display:column>
              </c:if>
              <c:if test="${line3.dokumenKewangan.noRujukanManual eq null}"><%-- or line2.dokumenKewangan.noRujukanManual eq ''}">--%>
                <display:column  title="No Resit"  >
                  <a href="#" onclick="edit3(this.form, '${line3.dokumenKewangan.idDokumenKewangan}');">
                    ${line3.dokumenKewangan.idDokumenKewangan}&nbsp;
                  </a>
                </display:column>
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
              <%--<display:column property="infoAudit.tarikhMasuk" title="Tarikh Transaksi" class="cawangan${line_rowNum}" format="{0,date,dd/MM/yyyy,hh:mm aa}"/>--%>
              <display:column property="untukTahun" title="Tahun" />
              <display:column property="status.nama" title="Status"/>
              <display:column title="Akaun Debit (RM)" style="text-align:right">
                <c:if test="${line3.status.kod eq 'A' and line3.kodTransaksi.kod ne '99000'
                              and line3.kodTransaksi.kod ne '99001'and line3.kodTransaksi.kod ne '99002'
                              and line3.kodTransaksi.kod ne '99051'and line3.kodTransaksi.kod ne '99050'
                              and line3.kodTransaksi.kod ne '99003'and line3.kodTransaksi.kod ne '99030' 
                              and line3.kodTransaksi.kod ne '99004' }">
                  <fmt:formatNumber value="${line3.amaun}" pattern="#,##0.00"/>
                </c:if>
                <c:if test="${line3.status.kod ne 'A'}">
                  -
                </c:if>
              </display:column>
              <display:column title="Akaun Kredit (RM)" style="text-align:right">
                <c:if test="${line3.status.kod ne 'A'}">
                  <fmt:formatNumber value="${line3.amaun}" pattern="#,##0.00"/>
                </c:if>
                <c:if test="${line3.status.kod eq 'A' and line3.kodTransaksi.kod eq '99002'
                              or line3.kodTransaksi.kod eq '99000'or line3.kodTransaksi.kod eq '99001'
                              or line3.kodTransaksi.kod eq '99051'or line3.kodTransaksi.kod eq '99050'
                              or line3.kodTransaksi.kod eq '99003'or line3.kodTransaksi.kod eq '99030'
                              or line3.kodTransaksi.kod eq '99004'}">
                  <fmt:formatNumber value="${line3.amaun}" pattern="#,##0.00"/>
                </c:if>
              </display:column>
              <display:column title="Dimasuk Oleh" >
                <c:out value="${line3.infoAudit.dimasukOleh.nama}"/>
              </display:column>
            </display:table>
          </div>
        </c:if>
      </fieldset>
    </div>
    <br>
  <c:if test="${actionBean.jum gt '0'}">
      <%--<c:if test="${actionBean.hakmilik.akaunCukai.baki gt '0'}">--%>
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
      </div>
    </c:if>
    </div>
</s:form>
<div align="center">
  <table border="0" width="97%">
    <tr>
      <td align="right" width="64%">
        <c:if test="${actionBean.pengguna.perananUtama.kod eq '2' or actionBean.pengguna.perananUtama.kod eq '5'}">
          <s:form beanclass="etanah.view.stripes.hasil.KutipanHasilActionBean">
            <s:text name="hakmilik.idHakmilik" value="${actionBean.hakmilik.idHakmilik}" style="display:none;"/>
            <c:if test="${!datun}">
              <%--<s:submit name="search" value="Bayar" class="btn"/>  08/04/2014 --%>
            </c:if>
          </s:form>
        </c:if>
      </td>
      <td align="left" width="33%">
        <%--<s:form beanclass="etanah.view.stripes.hasil.PertanyaanHakmilikActionBean">--%>
        <s:form beanclass="etanah.view.stripes.common.CarianHakmilik">
           <c:if test="${actionBean.hakmilik.kodStatusHakmilik.kod ne 'B'}">
           <c:if test="${actionBean.akaun.status.kod ne 'B'}">
              <s:button name="" onclick="cetakBil(this.form)" value="Cetak Bil" id="bil" class="btn" style="display:${actionBean.btn2}"/>
            </c:if> 
               </c:if>
         
          <%--<c:if test="${actionBean.kodNegeri ne 'melaka'}">--%>
          <s:button name="" onclick="cetakInfoCukai(this.form,'${actionBean.hakmilik.idHakmilik}');" value="Cetak Info Cukai" class="longbtn" style="display:${actionBean.btn2}"/>
          <%--</c:if>--%>
          <s:submit name="back" value="Kembali" class="btn" id="btn4"/>
          
        </s:form>
      </td>
    </tr>
  </table>
</div>