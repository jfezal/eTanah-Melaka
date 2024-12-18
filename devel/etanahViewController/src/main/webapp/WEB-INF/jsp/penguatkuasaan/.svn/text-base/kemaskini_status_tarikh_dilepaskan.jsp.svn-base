<%--
    Document   : kemaskini_status_tarikh_tuntutan
    Created on : Ogos 7, 2012, 2:16:37 AM
    Author     : sitifariza.hanim
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<c:if test="${multipleOp}">
    <style type="text/css">
        .tablecloth{
            padding:0px;
            width:90%;
        }

        .infoLP{
            float: right;
            font-weight: bold;
            color:#003194;
            font-family:Tahoma;
            font-size: 13px;

        }

        .infoHeader{
            font-weight: bold;
            color:#003194;
            font-family:Tahoma;
            font-size: 13px;
            text-align: center;

        }

    </style>
</c:if>
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

    });

    function popup(idBarang){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?viewBarangDetail&idBarang='+idBarang;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }

    function test(f) {
        $(f).clearForm();
    }

    function validate(){
        return true;
    }

</script>

<s:form beanclass="etanah.view.penguatkuasaan.KemaskiniStatusBarangRampasan">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Barang Rampasan
            </legend>
            <div class="content" align="center">
                <c:if test="${!multipleOp && !viewMultipleOp}">
                    <div class="instr-fieldset">
                        <font color="red">MAKLUMAN:</font> Sila kemaskini status barang rampasan dan tarikh dilepaskan.
                    </div>&nbsp;
                    <display:table class="tablecloth" name="${actionBean.senaraiBarangRampasan}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="/penguatkuasaan/kemaskini_status_barang_rampasan">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="Barang Rampasan"><a class="popup" onclick="popup(${line.idBarang})">${line.item}</a></display:column>
                        <display:column title="Kuantiti">
                            <c:if test="${line.kodKategoriItemRampasan.kod eq 'K'}">
                                Sebuah
                            </c:if>
                            <c:if test="${line.kodKategoriItemRampasan.kod eq 'B'}">
                                ${line.kuantiti} ${line.kuantitiUnit}
                            </c:if>
                        </display:column>
                        <display:column title="Tempat Simpanan">
                            <c:if test="${line.namaPemunya eq null}">
                                Tiada data
                            </c:if>
                            <c:if test="${line.namaPemunya ne null}">
                                ${line.namaPemunya}
                            </c:if>
                        </display:column>
                        <display:column title="Tempat Simpanan">
                            <c:if test="${line.tempatSimpanan eq null}">
                                Tiada data
                            </c:if>
                            <c:if test="${line.tempatSimpanan ne null}">
                                ${line.tempatSimpanan}
                            </c:if>
                        </display:column>
                        <display:column title="Status">
                            <input name="idBarang${line_rowNum-1}" value="${line.idBarang}" type="hidden">
                            <s:select name="status${line_rowNum-1}" id="status" value="${line.status.kod}">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodStatus}" label="nama" value="kod" />
                            </s:select>&nbsp;
                        </display:column>
                        <%-- <display:column title="Tarikh Dilepaskan">
                            <c:if test="${line.tarikhDilepas eq null}">
                                Tiada data
                            </c:if>
                            <c:if test="${line.tarikhDilepas ne null}">
                                ${line.tarikhDilepas}
                            </c:if>
                        </display:column>--%>

                    </display:table>

                    <table>
                        <tr>
                            <td align="right">
                                <s:button class="btn" value="Simpan" name="simpan" id="simpan" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
                            </td>
                        </tr>
                    </table>
                </c:if>


                <c:if test="${multipleOp}">
                    <div class="instr-fieldset">
                        <font color="red">MAKLUMAN:</font> Sila kemaskini status barang rampasan dan tarikh dilepaskan.
                    </div>&nbsp;
                    <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi">
                            <table width="20%" cellpadding="1">
                                <tr>
                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="20%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Tarikh laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                        <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                                    </td>
                                </tr>

                            </table>
                        </display:column>
                        <display:column title="Maklumat Barang Rampasan">
                            <c:set value="0" var="count"/>

                            <table width="100%" cellpadding="1">
                                <tr>
                                    <th  width="1%" align="center"><b>Bil</b></th>
                                    <th  width="1%" align="center"><b>Item</b></th>
                                    <th  width="5%" align="center"><b>Papar</b></th>
                                    <th  width="5%" align="center"><b>Status Barang</b></th>
                                    <th  width="5%" align="center"><b>Tarikh Dilepaskan</b></th>

                                </tr>
                                <c:forEach items="${line.senaraiBarangRampasan}" var="barang">
                                    <c:choose>
                                        <c:when test="${actionBean.stageRujukMahkamah eq true}">
                                            <c:if test="${barang.keputusan.kod eq 'RM'}">
                                                <tr>
                                                    <td width="5%">${count+1}</td>
                                                    <td width="50%"><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a></td>
                                                    <td width="30%">
                                                        <c:if test="${barang.imej.namaFizikal != null}">
                                                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                 onclick="doViewReport('${barang.imej.idDokumen}');" height="30" width="30" alt="papar"
                                                                 onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${barang.imej.kodDokumen.nama}"/>
                                                        </c:if>
                                                    </td>
                                                    <td width="30%">
                                                        <input name="idBarang${line_rowNum-1}${count}" value="${barang.idBarang}" type="hidden">
                                                        <s:select name="status${line_rowNum-1}${count}" id="status" value="${barang.status.kod}">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${actionBean.senaraiKodStatus}" label="nama" value="kod" />
                                                        </s:select>&nbsp;
                                                    </td>
                                                    <td width="30%">
                                                        <s:text name="tarikhDilepas${line_rowNum-1}${count}" id="tarikhDilepas${line_rowNum-1}${count}" value="${barang.tarikhDilepas}" size="12" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td width="5%">${count+1}</td>
                                                <td width="50%"><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a></td>
                                                <td width="30%">
                                                    <c:if test="${barang.imej.namaFizikal != null}">
                                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                             onclick="doViewReport('${barang.imej.idDokumen}');" height="30" width="30" alt="papar"
                                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${barang.imej.kodDokumen.nama}"/>
                                                    </c:if>
                                                </td>
                                                <td width="30%">
                                                    <input name="idBarang${line_rowNum-1}${count}" value="${barang.idBarang}" type="hidden">
                                                    <s:select name="status${line_rowNum-1}${count}" id="status" value="${barang.status.kod}">
                                                        <s:option value="">Sila Pilih</s:option>
                                                        <s:options-collection collection="${actionBean.senaraiKodStatus}" label="nama" value="kod" />
                                                    </s:select>&nbsp;
                                                </td>
                                                <td width="30%">
                                                    <s:text name="tarikhDilepas${line_rowNum-1}${count}" id="tarikhDilepas${line_rowNum-1}${count}" value="${barang.tarikhDilepas}" size="12" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;
                                                </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:set value="${count+1}" var="count"/>
                                </c:forEach>
                            </table>
                            <%-- <b>Jumlah : ${fn:length(line.senaraiBarangRampasan)}</b>--%>
                        </display:column>
                    </display:table>

                    <table>
                        <tr>
                            <td align="right">
                                <s:button class="btn" value="Simpan" name="simpanStatusBarangDilepaskan" id="simpan" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
                            </td>
                        </tr>
                    </table>
                </c:if>
                <c:if test="${viewMultipleOp}">
                    <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi" style="width:300px;">
                            <table width="20%" cellpadding="1">
                                <tr>
                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="20%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Tarikh laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                        <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                                    </td>
                                </tr>

                            </table>
                        </display:column>
                        <display:column title="Maklumat Barang Rampasan" style="width:700px;">
                            <c:set value="0" var="count"/>

                            <table width="70%" cellpadding="1">
                                <tr>
                                    <th  width="1%" align="center"><b>Bil</b></th>
                                    <th  width="1%" align="center"><b>Item</b></th>
                                    <th  width="5%" align="center"><b>Papar</b></th>
                                    <th  width="50%" align="center"><b>Status Barang</b></th>
                                    <th  width="50%" align="center"><b>Tarikh Dilepaskan</b></th>
                                </tr>
                                <c:forEach items="${line.senaraiBarangRampasan}" var="barang">
                                    <tr>
                                        <td width="5%">${count+1}</td>
                                        <td width="20%"><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a></td>
                                        <td width="10%">
                                            <c:if test="${barang.imej.namaFizikal != null}">
                                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                     onclick="doViewReport('${barang.imej.idDokumen}');" height="30" width="30" alt="papar"
                                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${barang.imej.kodDokumen.nama}"/>
                                            </c:if>
                                        </td>
                                        <td width="50%">
                                            ${barang.status.nama}
                                        </td>
                                        <td width="50%">
                                            ${barang.tarikhDilepas}
                                        </td>

                                    </tr>
                                    <c:set value="${count+1}" var="count"/>
                                </c:forEach>
                            </table>
                        </display:column>
                    </display:table>

                </c:if>
                <br>


                <br>
            </div>
        </fieldset>
    </div>
</s:form>
