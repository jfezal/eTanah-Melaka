<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/tablecloth.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready(function() {

        $('#displayBox').hide();
        $("#btn").hide();
    });
    function validate(stat, id) {
        $("#btn").show();
        var permohonan = ${actionBean.btn};
        if (stat == 'B') {
            alert("Nombor Resit " + id + " telah Batal.");
            $("#btn").attr("disabled", "true");
        } else if (permohonan) {
            alert("Tidak dibenarkan membuat pembatalan.");
            $("#btn").attr("disabled", "true");
        } else {
            $("#btn").removeAttr("disabled");
        }
    }

    function tambahTrans(idKewDok, noAkaun) {
        window.open("${pageContext.request.contextPath}/hasil/KemasukanResitCukaiTanahActionBean?tambahTransaksi&idKewDok=" + idKewDok + '&noAkaun=' + noAkaun, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }

    function removeKemaskiniFailUrusan(transaksi)
    {
        alert("transaksi = :" + transaksi)
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/hasil/KemasukanResitCukaiTanahActionBean?deleteTrans&transaksi='
                    + transaksi;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
//                        self.opener.refreshPageTanahRizab();
                    }, 'html');
        }
    }
</script>
<div class="subtitle">
    <table width="100%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">Kemaskini Maklumat Transaksi</font>
                </div>
            </td>
        </tr>
    </table>

    <s:form beanclass="etanah.view.stripes.hasil.KemasukanResitCukaiTanahActionBean" id="pembatalan_resit_1">
        <s:errors/>
        <s:messages/>
        <s:hidden name= "kodNegeri"/>
        <%--<s:form action="/pembatalan_resit" id="pembatalan_resit_1">--%>
        <s:hidden name="idDokumenKewangan" id="idDokumenKewangan" value="${actionBean.idDokumenKewangan}"/>
        <s:hidden name="noAkaun" id="noAkaun" value="${actionBean.noAkaun}"/>

        <s:hidden name="transaksi" id="transaksi" value="${actionBean.transaksi}"/>
        <c:if test="${actionBean.btnPindah ne 'tambah'}">
            <fieldset class="aras1">
                <legend>
                    Carian Maklumat
                </legend>
                <div class="instr-fieldset">
                    <font color="red">PERINGATAN:</font>Sila Masukan Salah Satu Maklumat Berikut
                </div>&nbsp;

                <div class="instr" align="center">                
                </div>

                <p>
                    <label for="No Resit">No Resit :</label>
                    <s:text name="idDokumenKewangan" id="idDokumenKewangan" value="idDokumenKewangan" size="15"/><br>

                </p>
                <p>
                    <label for="No Akaun">No Akaun :</label>
                    <s:text name="noAkaun" id="noAkaun" value="noAkaun" size="10"/>

                </p>
                <br>
                <table border="0" width="100%">
                    <tr>
                        <td align="center">
                            <s:submit name="search" value="Cari" class="btn"/>
                            <s:submit name="selectTransaction" value="Kembali" class="btn"/>
                        </td>
                    </tr>
                </table>

            </fieldset>

            <c:if test="${fn:length(actionBean.transList3) > 0}">
                <br>
                <center>
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <legend>
                                Maklumat Kesemua Transaksi
                            </legend>

                            <display:table class="tablecloth" name="${actionBean.transList3}" cellpadding="0" cellspacing="0" id="line"
                                           requestURI="${pageContext.request.contextPath}/hasil/KemasukanResitCukaiTanahActionBean">
                                <center>
                                    <display:column title="No">${line_rowNum}</display:column>
                                    <display:column property="kodTransaksi.nama" title="Nama Kod Trans" />
                                    <display:column property="kodTransaksi.kod" title="Kod Transaksi" />
                                    <display:column property="untukTahun" title="Tahun" />
                                    <display:column property="dokumenKewangan.idDokumenKewangan" title="No Resit" />
                                    <display:column property="amaun" title="Jumlah Bayaran(RM)" />
                                    <display:column property="status.nama" title="Status Bayaran"/>

                                </center>
                            </display:table>
                            <center>
                                <br>
                                <%--<s:button name=" " value="Tambah Lama" class="btn" onclick="tambahTrans('${actionBean.idDokumenKewangan}','${actionBean.noAkaun}');"/> --%> 
                                <c:if test="${actionBean.transBayarTotal ne 0}">
                                    <s:submit name="tambahTransaksi" value="Tambah" class="btn"/> 
                                    <s:submit name="search" value="Refresh" class="btn"/> 
                                </c:if>
                            </center>

                        </fieldset>
                    </div> 
                </center>


            </div>
        </c:if>

        <c:if test="${fn:length(actionBean.transList) > 0}">
            <br>
            <center>
                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>
                            Maklumat Transaksi untuk No Resit ${actionBean.idDokumenKewangan}
                        </legend>

                        <display:table class="tablecloth" name="${actionBean.transList}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/hasil/KemasukanResitCukaiTanahActionBean">
                            <center>
                                <display:column title="No">${line_rowNum}</display:column>
                                <display:column property="kodTransaksi.nama" title="Nama Kod Trans" />
                                <display:column property="kodTransaksi.kod" title="Kod Transaksi" />
                                <display:column property="untukTahun" title="Tahun" />
                                <display:column property="dokumenKewangan.idDokumenKewangan" title="No Resit" />
                                <display:column property="amaun" title="Jumlah Bayaran(RM)" />
                                <display:column property="status.nama" title="Status Bayaran"/>
                            </center>
                        </display:table>
                        <c:if test="${fn:length(actionBean.senaraiPenyataPemungut) <= 0}">
                            <br>
                            <s:submit name="simpanPP" value="Simpan PP" class="longbtn"/>
                        </c:if>

                    </fieldset>
                </div> 
            </center>


        </div>
    </c:if>

    <c:if test="${fn:length(actionBean.transList2) > 0}">
        <br>
        <center>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Transaksi Baru
                    </legend>
                    <display:table class="tablecloth" name="${actionBean.transList2}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/hasil/KemasukanResitCukaiTanahActionBean">
                        <center>
                            <display:column title="No">${line_rowNum}</display:column>
                            <display:column property="kodTransaksi.nama" title="Nama Kod Trans" />
                            <display:column property="kodTransaksi.kod" title="Kod Transaksi" />
                            <display:column property="untukTahun" title="Tahun" />
                            <display:column property="dokumenKewangan.idDokumenKewangan" title="No Resit" />
                            <display:column property="amaun" title="Jumlah Bayaran(RM)" />
                            <display:column property="status.nama" title="Status Bayaran"/>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="removeKemaskiniFailUrusan('${line.idTransaksi}');" />
                                </div>
                            </display:column>
                        </center>
                    </display:table>
                </fieldset>
                <br>
                <s:submit name="simpanTrans" value="Simpan Transaksi" class="longbtn"/>
                <c:if test="${actionBean.btnPindah eq 'ResitPenuh'}">
                    <s:submit name="search" value="Refresh" class="btn"/> 
                </c:if>

            </div>
        </center>
    </c:if>
</c:if>


<c:if test="${actionBean.btnPindah eq 'tambah'}">
    <s:hidden name= "kodNegeri"/>
    <%--<s:form action="/pembatalan_resit" id="pembatalan_resit_1">--%>
    <fieldset class="aras1">
        <legend>
            Carian Maklumat
        </legend>
        <div class="instr-fieldset">
            <font color="red">PERINGATAN:</font>Sila Masukan Salah Satu Maklumat Berikut
        </div>&nbsp;

        <div class="instr" align="center">                
        </div>

        <p>
            <label for="No Resit">No Resit :</label>
            ${actionBean.idDokumenKewangan}
        </p>
        <p>
            <label for="No Akaun">No Akaun :</label>
            ${actionBean.noAkaun}
        </p>
        <p>
            <label for="Amaun Resit">Amaun Resit :</label>
            RM ${actionBean.dokumenKewangan.amaunBayaran}  ( <font color="red">PERINGATAN: Pastikan nilai tidak melebihi amaun resit</font> )
        </p>
        <p>
            <label for="Amaun Resit">Baki Amaun Resit :</label>
            RM ${actionBean.transBayarTotal} <font color="red">Adalah Baki Amaun Untuk Resit Ini</font>
        </p>

        <p>
            <label for="Kod Transaksi">Kod Transaksi :</label>
            <s:select name="kodTransbaru" id="kodTransbaru">
                <s:option value="">-- Sila Pilih --</s:option>
                <c:forEach items="${actionBean.senaraiKodTrans}" var="item" varStatus="line">                         
                    <s:option value="${item.kod}" style="width:100px">
                        ${item.kod} - ( ${item.nama} )
                    </s:option>
                </c:forEach>
            </s:select>
        </p>

        <p>
            <label for="Untuk Tahun">Untuk Tahun :</label>
            <s:text name="utkTahun" id="utkTahun" size="10"/><br>
        </p>
        <p>
            <label for="Amaun Resit Pecahan">Amaun Resit Pecahan(RM) :</label>
            <s:text name="amaunBaru" id="amaunBaru" value="amaunBaru" size="10"/><br>
        </p>

    </fieldset>
    <table border="0" width="100%">
        <tr>
            <td align="right">
                <s:submit name="checkProcess" value="checkProcess" class="btn"/>
                <s:submit name="search" value="Kembali" class="btn"/>
            </td>
        </tr>
    </table>
</c:if>


</s:form>








