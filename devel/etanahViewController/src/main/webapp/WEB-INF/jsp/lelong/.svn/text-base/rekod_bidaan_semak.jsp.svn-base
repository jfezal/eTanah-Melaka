<%-- 
    Document   : rekod_bidaan_semak
    Created on : Aug 2, 2010, 2:47:04 PM
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
    function popupReset(id){
        if(confirm("Anda pasti untuk kemaskini baki kepada RM 0.00?")){
            var url = "${pageContext.request.contextPath}/lelong/keputusan_bidaan?reset&idLelong="+id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function popupPembida(val){
        window.open("${pageContext.request.contextPath}/lelong/keputusan_bidaan?pembidaPopupSemak&idLelong="+val, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

</script>


<s:form beanclass="etanah.view.stripes.lelong.KeputusanBidaanActionBean">
    <s:messages/>
    <s:errors/>&nbsp;
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pembida  
            </legend><br>

            <c:if test="${actionBean.show eq false}">
                <c:if test="${actionBean.rujuk eq false}">
                    <p>
                        <label> Bilangan Lelongan : </label>
                        ${actionBean.lelong.bil}&nbsp;
                    </p>
                    <p>
                        <label> Status Lelongan : </label>Tiada Pembida 
                    </p><br>
                </c:if>

            </c:if>
            <c:if test="${actionBean.rujuk eq true}">
                <p>
                    <label> Status Lelongan : </label>Rujuk Mahkamah
                </p><br>
            </c:if>
            <c:if test="${actionBean.show eq true}">
                <c:if test="${actionBean.negeri eq true}">
                    <c:if test="${actionBean.bida eq true}">
                        <c:if test="${actionBean.pihak ne null}">
                            <p>
                                <label> Bilangan Lelongan : </label>
                                ${actionBean.lelong.bil}&nbsp;
                            </p>
                            <p>
                                <label>Status : </label>
                                ${actionBean.lelong.baki <= 0 ? "TELAH JELAS BAKI" : "BELUM BAYAR BAKI" } &nbsp;
                            </p>
                            <br>
                            <p>
                                <label> Nama :</label>
                                <font style="text-transform:uppercase;"> ${actionBean.pihak.nama}</font>&nbsp;
                            </p>
                            <p>
                                <label>Jenis Pengenalan: </label>
                                <font style="text-transform:uppercase;">${actionBean.pihak.jenisPengenalan.nama}</font>&nbsp;
                            </p>
                            <p>
                                <label> Nombor Pengenalan: </label>
                                ${actionBean.pihak.noPengenalan}&nbsp;
                            </p>
                            <p>
                                <label>Alamat: </label>
                                <font style="text-transform: uppercase">${actionBean.pihak.alamat1}</font> <c:if test="${actionBean.pihak.alamat2 ne null}">,</c:if>
                                <font style="text-transform:uppercase;">${actionBean.pihak.alamat2}</font> <c:if test="${actionBean.pihak.alamat3 ne null}">,</c:if>
                                <font style="text-transform:uppercase;">${actionBean.pihak.alamat3}</font> <c:if test="${actionBean.pihak.alamat4 ne null}">,</c:if>
                                <font style="text-transform:uppercase;">${actionBean.pihak.alamat4}</font> <c:if test="${actionBean.pihak.poskod ne null}">,</c:if>
                                ${actionBean.pihak.poskod}<c:if test="${actionBean.pihak.negeri.kod ne null}">,</c:if>
                                <font style="text-transform:uppercase;">${actionBean.pihak.negeri.nama}</font> &nbsp;
                            </p>
                            <p>
                                <label> Nombor Telefon Rumah: </label>
                                ${actionBean.pihak.noTelefon1}&nbsp;
                            </p>
                            <p>
                                <label> Nombor Telefon Bimbit: </label>
                                ${actionBean.pihak.noTelefonBimbit}&nbsp;
                            </p>
                            <br>
                            <p>
                                <label>Tarikh Lelongan Awam:</label>
                                <fmt:formatDate value="${actionBean.lelong.tarikhLelong}" pattern="dd/MM/yyyy"/>&nbsp;
                            </p>
                            <p>
                                <label>Tarikh Akhir Bayaran : </label>
                                <fmt:formatDate value="${actionBean.lelong.tarikhAkhirBayaran}" pattern="dd/MM/yyyy"/>&nbsp;
                                <s:hidden name="tarikhAkhirBayaran" id="tarikhBayar" formatPattern="dd/MM/yyyy"/>
                                <s:hidden name="tarikhLelong" id="tarikhLelong" formatPattern="dd/MM/yyyy"/>
                            </p>
                            <p>
                                <label> Harga Rizab (RM): </label>
                                <s:format formatPattern="#,##0.00" value="${actionBean.lelong.hargaRizab}" /> &nbsp;
                            </p>
                            <p>
                                <label> Harga Bidaan (RM): </label>
                                <s:format formatPattern="#,##0.00" value="${actionBean.lelong.hargaBidaan}" /> &nbsp;
                            </p>
                            <c:if test="${actionBean.atd.amaun ne null}">
                                <p>
                                    <label>Deposit Awal (RM): </label>
                                    <s:format formatPattern="#,##0.00" value="${actionBean.atd.amaun}" /> &nbsp;
                                </p>
                                <p><label>Cara Bayaran : </label>
                                    ${actionBean.atd.caraBayaran.nama}
                                </p>
                            </c:if>

                            <c:if test="${actionBean.atd2.jenisDeposit eq 'T'}">
                                <p>
                                    <label>Deposit Tambahan(RM): </label>
                                    <s:format formatPattern="#,##0.00" value="${actionBean.atd2.amaun}" /> &nbsp;
                                </p>
                                <p><label>Cara Bayaran : </label>
                                    ${actionBean.atd2.caraBayaran.nama}
                                </p>
                            </c:if>
                            <p>
                                <label>Baki (RM): </label>
                                <s:format formatPattern="#,##0.00" value="${actionBean.lelong.baki}" /> &nbsp;
                            </p>
                            &nbsp;

                            <c:if test="${dahBayar eq true}">
                                <c:if test="${actionBean.disabled1 ne true}">
                                    <br>
                                    <p align="center">
                                        <label></label>
                                        <s:button name="kembali2" value="Kembali" onclick="doSubmit(this.form, this.name, 'page_div')" class="btn" />
                                    </p><br>
                                </c:if>
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.pihak eq null}">
                            Status Pembida : Tiada Pembida
                        </c:if>
                    </c:if>
                </c:if>
                <c:if test="${actionBean.negeri ne true}">
                    <c:if test="${actionBean.bida eq true}">
                        <c:if test="${actionBean.pihak ne null}">
                            <p>
                                <label> Bilangan Lelongan : </label>
                                ${actionBean.lelong.bil}&nbsp;
                            </p>
                            <p>
                                <label>Status : </label>
                                ${actionBean.lelong.baki <= 0 ? "TELAH JELAS BAKI" : "BELUM JELAS BAKI" } &nbsp;
                            </p>
                            <br>
                            <p>
                                <label> Nama :</label>
                                <font style="text-transform:uppercase;"> ${actionBean.pihak.nama}</font>&nbsp;
                            </p>
                            <p>
                                <label>Jenis Pengenalan: </label>
                                <font style="text-transform:uppercase;">${actionBean.pihak.jenisPengenalan.nama}</font>&nbsp;
                            </p>
                            <p>
                                <label> Nombor Pengenalan: </label>
                                ${actionBean.pihak.noPengenalan}&nbsp;
                            </p>
                            <p>
                                <label>Alamat: </label>
                                <font style="text-transform: uppercase">${actionBean.pihak.alamat1}</font> <c:if test="${actionBean.pihak.alamat2 ne null}">,</c:if>
                                <font style="text-transform:uppercase;">${actionBean.pihak.alamat2}</font> <c:if test="${actionBean.pihak.alamat3 ne null}">,</c:if>
                                <font style="text-transform:uppercase;">${actionBean.pihak.alamat3}</font> <c:if test="${actionBean.pihak.alamat4 ne null}">,</c:if>
                                <font style="text-transform:uppercase;">${actionBean.pihak.alamat4}</font> <c:if test="${actionBean.pihak.poskod ne null}">,</c:if>
                                ${actionBean.pihak.poskod}<c:if test="${actionBean.pihak.negeri.kod ne null}">,</c:if>
                                <font style="text-transform:uppercase;">${actionBean.pihak.negeri.nama}</font> &nbsp;
                            </p>
                            <p>
                                <label> Nombor Telefon Rumah: </label>
                                ${actionBean.pihak.noTelefon1}&nbsp;
                            </p>
                            <p>
                                <label> Nombor Telefon Bimbit: </label>
                                ${actionBean.pihak.noTelefonBimbit}&nbsp;
                            </p>
                            <br>
                            <p>
                                <label>Tarikh Lelongan Awam:</label>
                                <fmt:formatDate value="${actionBean.lelong.tarikhLelong}" pattern="dd/MM/yyyy"/>&nbsp;
                            </p>
                            <p>
                                <label>Tarikh Akhir Bayaran : </label>
                                <fmt:formatDate value="${actionBean.lelong.tarikhAkhirBayaran}" pattern="dd/MM/yyyy"/>&nbsp;
                                <s:hidden name="tarikhAkhirBayaran" id="tarikhBayar" formatPattern="dd/MM/yyyy"/>
                                <s:hidden name="tarikhLelong" id="tarikhLelong" formatPattern="dd/MM/yyyy"/>
                            </p>
                            <p>
                                <label> Harga Rizab (RM): </label>
                                <s:format formatPattern="#,##0.00" value="${actionBean.lelong.hargaRizab}" /> &nbsp;
                            </p>
                            <p>
                                <label> Harga Bidaan (RM): </label>
                                <s:format formatPattern="#,##0.00" value="${actionBean.lelong.hargaBidaan}" /> &nbsp;
                            </p>
                            <c:if test="${actionBean.atd.amaun ne null}">
                                <p>
                                    <label>Deposit Awal (RM): </label>
                                    <s:format formatPattern="#,##0.00" value="${actionBean.atd.amaun}" /> &nbsp;
                                </p>
                                <p><label>Cara Bayaran : </label>
                                    ${actionBean.atd.caraBayaran.nama}
                                </p>
                            </c:if>
                            <c:if test="${actionBean.atd2.jenisDeposit eq 'T'}">
                                <p>
                                    <label>Deposit Tambahan(RM): </label>
                                    <s:format formatPattern="#,##0.00" value="${actionBean.atd2.amaun}" /> &nbsp;
                                </p>
                                <p><label>Cara Bayaran : </label>
                                    ${actionBean.atd2.caraBayaran.nama}
                                </p>
                            </c:if>
                            <p>
                                <label>Baki (RM): </label>
                                <s:format formatPattern="#,##0.00" value="${actionBean.lelong.baki}" /> &nbsp;
                            </p>
                            &nbsp;

                            <c:if test="${dahBayar eq true}">
                                <c:if test="${actionBean.disabled1 ne true}">
                                    <br>
                                    <p align="center">
                                        <label></label>
                                        <s:button name="kembali2" value="Kembali" onclick="doSubmit(this.form, this.name, 'page_div')" class="btn" />
                                    </p><br>
                                </c:if>
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.pihak eq null}">
                            Status Pembida : Tiada Pembida
                        </c:if>
                    </c:if>
                </c:if>

                <c:if test="${actionBean.bida eq false}">
                    <c:if test="${fn:length(actionBean.senaraiLelongan2) == 0}">
                        Status Pembida : Tiada Pembida
                    </c:if>
                    <c:if test="${actionBean.negeri eq true}">
                        <c:if test="${fn:length(actionBean.senaraiLelongan2) > 0}">
                            <div align="center">
                                <display:table class="tablecloth" name="${actionBean.senaraiLelongan2}" id="line">
                                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                                    <display:column title="IDHakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik" class="rowLine"/>
                                    <display:column title="Status Lelongan">
                                        <c:if test="${line.kodStatusLelongan.kod ne 'TP'}">
                                            <c:if test="${line.pembida.idPihak ne null}">
                                                Ada Pembida
                                            </c:if>
                                            <c:if test="${line.pembida.idPihak eq null}">
                                                Tiada Pembida
                                            </c:if>
                                        </c:if>
                                        <c:if test="${line.kodStatusLelongan.kod eq 'TP'}">
                                            TARIK PERMOHONAN
                                        </c:if>
                                    </display:column>
                                    <display:column title="Pembida">
                                        <c:if test="${line.kodStatusLelongan.kod ne 'TP'}">
                                            <c:if test="${line.pembida.idPihak eq null}">
                                                <div id="tiada${line_rowNum - 1}" >
                                                    Tiada Pembida
                                                </div>
                                            </c:if>
                                            <c:if test="${line.pembida.idPihak ne null}">
                                                ${line.pembida.nama}
                                                <ul>
                                                    <li>
                                                        <a onclick="popupPembida('${line.idLelong}');return false;" onmouseover="this.style.cursor='pointer';" title="Sila Klik Untuk Semak Maklumat Pembida">
                                                            <img alt="Sila Klik Untuk Semak Maklumat Pembida" src='${pageContext.request.contextPath}/pub/images/edit.gif' />
                                                        </a>
                                                    </li>
                                                </ul>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${line.kodStatusLelongan.kod eq 'TP'}">
                                            TARIK PERMOHONAN
                                        </c:if>
                                    </display:column>
                                    <c:if test="${AT eq true}">        
                                        <display:column title="Baki">
                                            <c:if test="${line.kodStatusLelongan.kod ne 'TP'}">
                                                RM <fmt:formatNumber value="${line.baki}" pattern="#,##0.00"/>
                                            </c:if>
                                            <c:if test="${line.kodStatusLelongan.kod eq 'TP'}">
                                                -
                                            </c:if>
                                        </display:column>

                                        <display:column title="Status Bayaran" >
                                            <c:if test="${line.kodStatusLelongan.kod ne 'TP'}">
                                                <c:if test="${line.baki ne 0}">
                                                    Belum Bayar Baki
                                                </c:if>
                                                <c:if test="${line.baki eq 0}">
                                                    Telah Bayar Baki
                                                </c:if>
                                            </c:if>
                                            <c:if test="${line.kodStatusLelongan.kod eq 'TP'}">
                                                -
                                            </c:if>
                                        </display:column>
                                    </c:if>
                                    <c:if test="${dahBayar eq true}">
                                        <display:column title="Baki">
                                            <c:if test="${line.kodStatusLelongan.kod ne 'TP'}">
                                                RM <fmt:formatNumber value="${line.baki}" pattern="#,##0.00"/>
                                            </c:if>
                                            <c:if test="${line.kodStatusLelongan.kod eq 'TP'}">
                                                -
                                            </c:if>
                                        </display:column>
                                        <display:column title="Status">
                                            <c:if test="${line.kodStatusLelongan.kod ne 'TP'}">
                                                ${line.baki <= 0 ? "TELAH JELAS BAKI" : "BELUM JELAS BAKI" }
                                            </c:if>
                                            <c:if test="${line.kodStatusLelongan.kod eq 'TP'}">
                                                -
                                            </c:if>
                                        </display:column>    
                                    </c:if>
                                </display:table>
                            </div>
                        </c:if>
                    </c:if>

                    <c:if test="${actionBean.negeri ne true}">
                        <c:if test="${fn:length(actionBean.senaraiLelongan2) > 0}">
                            <div align="center">
                                <display:table class="tablecloth" name="${actionBean.senaraiLelongan2}" id="line">
                                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                                    <display:column title="IDHakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik" class="rowLine"/>
                                    <display:column title="Status Lelongan">
                                        <c:forEach items="${actionBean.senaraiPembida}" var="line2">
                                            <c:if test="${line2.lelong.idLelong eq line.idLelong}">
                                                <c:if test="${line.kodStatusLelongan.kod ne 'TP'}">
                                                    <c:if test="${line2.kodStsPembida.kod eq 'BJ'}">
                                                        Ada Pembida
                                                    </c:if>
                                                    <c:if test="${line2.kodStsPembida.kod eq 'AT'}">
                                                        Ada Pembida
                                                    </c:if>
                                                    <c:if test="${line2.kodStsPembida.kod eq 'TB'}">
                                                        Tiada Pembida 
                                                    </c:if>
                                                </c:if>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${line.kodStatusLelongan.kod eq 'TP'}">
                                            TARIK PERMOHONAN
                                        </c:if>
                                    </display:column>
                                    <display:column title="Pembida">
                                        <c:forEach items="${actionBean.senaraiPembida}" var="line2">
                                            <c:if test="${line2.lelong.idLelong eq line.idLelong}">
                                                <c:if test="${line.kodStatusLelongan.kod ne 'TP'}">
                                                    <c:if test="${line2.kodStsPembida.kod eq 'TB'}">
                                                        <div id="tiada${line_rowNum - 1}" >
                                                            Tiada Pembida 
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${line2.kodStsPembida.kod eq 'BJ'}">
                                                        ${line2.pihak.nama}
                                                        <ul>
                                                            <li>
                                                                <a onclick="popupPembida('${line.idLelong}');return false;" onmouseover="this.style.cursor='pointer';" title="Sila Klik Untuk Semak Maklumat Pembida">
                                                                    <img alt="Sila Klik Untuk Semak Maklumat Pembida" src='${pageContext.request.contextPath}/pub/images/edit.gif' />
                                                                </a>
                                                            </li>
                                                        </ul>
                                                    </c:if>
                                                    <c:if test="${line2.kodStsPembida.kod eq 'AT'}">
                                                        ${line2.pihak.nama}
                                                        <ul>
                                                            <li>
                                                                <a onclick="popupPembida('${line.idLelong}');return false;" onmouseover="this.style.cursor='pointer';" title="Sila Klik Untuk Semak Maklumat Pembida">
                                                                    <img alt="Sila Klik Untuk Semak Maklumat Pembida" src='${pageContext.request.contextPath}/pub/images/edit.gif' />
                                                                </a>
                                                            </li>
                                                        </ul>
                                                    </c:if>
                                                </c:if>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${line.kodStatusLelongan.kod eq 'TP'}">
                                            TARIK PERMOHONAN
                                        </c:if>
                                    </display:column>
                                    <c:if test="${AT eq true}">        
                                        <display:column title="Baki">
                                            <c:if test="${line.kodStatusLelongan.kod ne 'TP'}">
                                                RM <fmt:formatNumber value="${line.baki}" pattern="#,##0.00"/>
                                            </c:if>
                                            <c:if test="${line.kodStatusLelongan.kod eq 'TP'}">
                                                -
                                            </c:if>
                                        </display:column>

                                        <display:column title="Status Bayaran" >
                                            <c:if test="${line.kodStatusLelongan.kod ne 'TP'}">
                                                <c:if test="${line.baki ne 0}">
                                                    Belum Bayar Baki
                                                </c:if>
                                                <c:if test="${line.baki eq 0}">
                                                    Telah Bayar Baki
                                                </c:if>
                                            </c:if>
                                            <c:if test="${line.kodStatusLelongan.kod eq 'TP'}">
                                                -
                                            </c:if>
                                        </display:column>
                                    </c:if>
                                    <c:if test="${dahBayar eq true}">
                                        <display:column title="Baki">
                                            <c:if test="${line.kodStatusLelongan.kod ne 'TP'}">
                                                RM <fmt:formatNumber value="${line.baki}" pattern="#,##0.00"/>
                                            </c:if>
                                            <c:if test="${line.kodStatusLelongan.kod eq 'TP'}">
                                                -
                                            </c:if>
                                        </display:column>
                                        <display:column title="Status">
                                            <c:if test="${line.kodStatusLelongan.kod ne 'TP'}">
                                                ${line.baki <= 0 ? "TELAH JELAS BAKI" : "BELUM JELAS BAKI" }
                                            </c:if>
                                            <c:if test="${line.kodStatusLelongan.kod eq 'TP'}">
                                                -
                                            </c:if>
                                        </display:column>    
                                    </c:if>
                                </display:table>
                            </div>
                        </c:if>
                    </c:if>

                    <br><br>
                    <c:if test="${dahBayar eq true}">
                        <p align="center">
                            <s:button name="kembali2" value="Kembali" onclick="doSubmit(this.form, this.name, 'page_div')" class="btn" />
                        </p>
                    </c:if>
                </c:if>
            </c:if>
        </fieldset>
    </div>
</s:form>
