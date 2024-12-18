<%--
    Document   : maklumat_asasAPT
    Created on : Jan 15, 2020, 4:01:04 PM
    Author     : zipzap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready(function() {

        var kodNegeri = $('#kodNegeri').val();
        var stageId = $('#stageId').val();
        var kodUrusan = $('#kodUrusan').val();
        var resultCase = $('#keputusanKes').val();

        if (kodNegeri == '04' && (stageId == '17ArahanMaklumanKpsn')) {
            if (kodUrusan == 'PTPT') {
                if ($("#keputusanId").val() == 'PV') {
                    $(".warning").html("Arahan : Terdapat " + $("#keputusanName").val() + ". Sila rujuk Tab Laporan Kerosakan Tanah");
                } else {
                    $(".warning").html("Arahan : " + $("#keputusanName").val() + ". Tiada perubahan");
                }
            }
        }
        else if (resultCase == "T") {
            if (kodUrusan == 'JMRE') {
                $('#page_id_006').hide();//hide tab draf MMK
            }
        }

    });
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form  beanclass="etanah.view.stripes.pengambilan.share.common.MaklumatAsasPengambilanActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="kodKeputusan.kod" id="keputusanId" />
    <s:hidden name="kodKeputusan.nama" id="keputusanName" />
    <s:hidden name="kodNegeri" id="kodNegeri"/>
    <s:hidden name="stageId" id="stageId"/>
    <s:hidden name="permohonan.kodUrusan.kod" id="kodUrusan"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Permohonan</legend>
            <p>
                <label for="Permohonan">Permohonan :</label>
                <font style="text-transform:uppercase;"> ${actionBean.permohonan.kodUrusan.nama}</font>&nbsp;
            </p>
            <p>
                <label for="ID Permohonan">ID Permohonan :</label>
                ${actionBean.permohonan.idPermohonan}&nbsp;
            </p>
        </fieldset>
    </div>

    <br/>
    <table style="width: 100%">
        <tr><td><div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Penyerah</legend>
            <p>
                <label>Nama :</label>
                <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahNama}</font>&nbsp;
            </p>
            <p>
                <label>Jenis Penyerah :</label>
                <font style="text-transform:uppercase;">
                    <c:if test="${actionBean.permohonan.kodPenyerah.nama ne null}"> ${actionBean.permohonan.kodPenyerah.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.kodPenyerah eq null && actionBean.permohonan.penyerahJenisPengenalan ne null}">
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'B'}"> INDIVIDU </c:if><%--K/P Baru --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'D'}"> SYARIKAT </c:if><%-- Pendaftaran--%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'F'}">  INDIVIDU</c:if><%-- Paksa--%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'I'}">  INDIVIDU</c:if><%--Polis --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'L'}"> INDIVIDU </c:if><%-- K/P Lama--%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'N'}"> SYARIKAT </c:if><%--Bank --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'P'}"> INDIVIDU </c:if><%--Pasport --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'S'}"> SYARIKAT </c:if><%--Syarikat --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'U'}"> PERTUBUHAN </c:if><%--Pertubuhan --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'X'}"> TIADA </c:if><%-- Tiada --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'Z'}"> Badan Kerajaan </c:if><%--Badan Kerajaan --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'O'}"> Tidak Berkenaan </c:if><%--Tidak Berkenaan --%>
                    </c:if>
                </font>
            </p>
            <c:if test="${actionBean.permohonan.penyerahAlamat1 ne null}">
                <p>
                    <label>Alamat :</label>
                    <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahAlamat1}</font>&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahAlamat2 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;">${actionBean.permohonan.penyerahAlamat2}</font> &nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahAlamat3 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahAlamat3} </font> &nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahAlamat4 ne null}">
                <p>
                    <label>Bandar :</label>
                    <font style="text-transform:uppercase;">${actionBean.permohonan.penyerahAlamat4}</font>&nbsp;
                </p>
            </c:if>
            <p>
                <label>Poskod :</label>
                ${actionBean.permohonan.penyerahPoskod}&nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                <font style="text-transform:uppercase;">${actionBean.permohonan.penyerahNegeri.nama} </font>&nbsp;
            </p>
        </fieldset>
    </div></td><td><div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pemohon</legend>
            <p>
                <label>Nama :</label>
                <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahNama}</font>&nbsp;
            </p>
            <p>
                <label>Jenis Penyerah :</label>
                <font style="text-transform:uppercase;">
                    <c:if test="${actionBean.permohonan.kodPenyerah.nama ne null}"> ${actionBean.permohonan.kodPenyerah.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.kodPenyerah eq null && actionBean.permohonan.penyerahJenisPengenalan ne null}">
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'B'}"> INDIVIDU </c:if><%--K/P Baru --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'D'}"> SYARIKAT </c:if><%-- Pendaftaran--%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'F'}">  INDIVIDU</c:if><%-- Paksa--%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'I'}">  INDIVIDU</c:if><%--Polis --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'L'}"> INDIVIDU </c:if><%-- K/P Lama--%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'N'}"> SYARIKAT </c:if><%--Bank --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'P'}"> INDIVIDU </c:if><%--Pasport --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'S'}"> SYARIKAT </c:if><%--Syarikat --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'U'}"> PERTUBUHAN </c:if><%--Pertubuhan --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'X'}"> TIADA </c:if><%-- Tiada --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'Z'}"> Badan Kerajaan </c:if><%--Badan Kerajaan --%>
                        <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'O'}"> Tidak Berkenaan </c:if><%--Tidak Berkenaan --%>
                    </c:if>
                </font>
            </p>
            <c:if test="${actionBean.permohonan.penyerahAlamat1 ne null}">
                <p>
                    <label>Alamat :</label>
                    <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahAlamat1}</font>&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahAlamat2 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;">${actionBean.permohonan.penyerahAlamat2}</font> &nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahAlamat3 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahAlamat3} </font> &nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahAlamat4 ne null}">
                <p>
                    <label>Bandar :</label>
                    <font style="text-transform:uppercase;">${actionBean.permohonan.penyerahAlamat4}</font>&nbsp;
                </p>
            </c:if>
            <p>
                <label>Poskod :</label>
                ${actionBean.permohonan.penyerahPoskod}&nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                <font style="text-transform:uppercase;">${actionBean.permohonan.penyerahNegeri.nama} </font>&nbsp;
            </p>
        </fieldset>
    </div></td></tr>
        
    </table>

    <fieldset class="aras1">
        <legend>Senarai Pemilik/Pihak Berkepentingan </legend>
        <div class="content" align="center">
            <label for="Maklumat Pengambilan">Bilangan Pihak Berkepentingan :&nbsp;${actionBean.size}</label>&nbsp;
            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList2}" cellpadding="0" cellspacing="0" id="line"
                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                <display:column title="No. Hakmilik" ><%--${line.hakmilik.kodHakmilik.kod}--%>${line.hakmilik.idHakmilik}</display:column>
                <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                <%--No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>--%>
                <display:column title="Tuan Tanah">
                    <c:set value="1" var="count"/>
                    <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                        <c:if test="${senarai.jenis.kod eq 'PM' && senarai.aktif eq 'Y'}">
                            <table border="0">
                                <tr>
                                    <td><c:out value="${count}"/>)</td>
                                    <td><font style="text-transform:uppercase;"><c:out value="${senarai.pihak.nama}"/></font></td>
                                    <td>(<c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/>)</td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'B'}">
                                        <td>No.KP Baru :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'L'}">
                                        <td>No.KP Lama :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'S'}">
                                        <td>No.Syarikat :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'D'}">
                                        <td>No.Pendaftaran :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'F'}">
                                        <td>No.Paksa :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'I'}">
                                        <td>No.Polis :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'K'}">
                                        <td>No.MyKid :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'N'}">
                                        <td>No.Bank :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'P'}">
                                        <td>No.Passport :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'T'}">
                                        <td>No.Tentera :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'U'}">
                                        <td>No.Pertubuhan :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <td>&nbsp;</td>
                                </tr>
                            </table>
                            <c:set value="${count + 1}" var="count"/>
                        </c:if>
                    </c:forEach>

                </display:column>
                <%--<display:column title="Syer yang dimiliki">
               <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                   <c:if test="${senarai.jenis.kod eq 'PM'}">
                      <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/><br>
                      </c:if>
               </c:forEach>
               </display:column>--%>
                <display:column title="Pihak Berkepentingan">
                    <%--<c:set value="1" var="count"/>
                <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                    <c:if test="${senarai.jenis.kod ne 'PM'}">
                        <br>
                        <c:out value="${count}"/>)&nbsp;
                           <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;
                           <c:set value="${count + 1}" var="count"/><br>
                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co:<c:out value="${senarai.pihak.noPengenalan}"/>
                            </c:if>
                </c:forEach>--%>

                    <c:set value="1" var="count"/>
                    <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                        <c:if test="${senarai.jenis.kod ne 'PM' && senarai.aktif eq 'Y'}">
                            <table border="0">
                                <tr>
                                    <td><c:out value="${count}"/>)</td>
                                    <td><c:out value="${senarai.pihak.nama}"/></td>
                                    <td>&nbsp;</td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <%--<td>No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/></td>--%>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'B'}">
                                        <td>No.KP Baru :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'L'}">
                                        <td>No.KP Lama :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'S'}">
                                        <td>No.Syarikat :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'D'}">
                                        <td>No.Pendaftaran :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'F'}">
                                        <td>No.Paksa :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'I'}">
                                        <td>No.Polis :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'K'}">
                                        <td>No.MyKid :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'N'}">
                                        <td>No.Bank :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'P'}">
                                        <td>No.Passport :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'T'}">
                                        <td>No.Tentera :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'U'}">
                                        <td>No.Pertubuhan :&nbsp;
                                            <c:out value="${senarai.pihak.noPengenalan}"/>
                                        </td>
                                    </c:if>
                                    <td>&nbsp;</td>
                                </tr>
                            </table>
                            <c:set value="${count + 1}" var="count"/>
                        </c:if>
                    </c:forEach>
                </display:column>
                <display:column title="Jenis Pihak Berkepentingan">
                    <c:set value="1" var="count"/>
                    <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                        <c:if test="${senarai.jenis.kod ne 'PM' && senarai.aktif eq 'Y'}">
                            <table border="0">
                                <tr>
                                    <td><c:out value="${count}"/>)</td>
                                    <td><c:out value="${senarai.jenis.nama}"/></td>
                                    <td>&nbsp;</td>
                                </tr>
                            </table>
                            <c:set value="${count + 1}" var="count"/>
                        </c:if>
                    </c:forEach>

                </display:column>
                <%--<display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>--%>
                <display:column title="Tarikh Pemilikan Didaftar">
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                </display:column>
                <%--<display:column property="jenis.nama" title="Jenis Pihak"/>--%>

            </display:table>

        </div>

    </fieldset>

</s:form>
