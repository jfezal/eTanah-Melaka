<%-- 
    Document   : semak_pembida_info
    Created on : 29 Mei 2013, 3:39:17 PM
    Author     : Mazurahayati
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
            <p>
                <c:if test="${actionBean.mlk eq true}">
                    <br>
                <p>
                    <label> Nama :</label>
                    <font style="text-transform:uppercase;"> ${actionBean.pembida.pihak.nama}</font>&nbsp;
                </p>
                <p>
                    <label>Jenis Pengenalan: </label>
                    <font style="text-transform:uppercase;">${actionBean.pembida.pihak.jenisPengenalan.nama}</font>&nbsp;
                </p>
                <p>
                    <label> Nombor Pengenalan: </label>
                    ${actionBean.pembida.pihak.noPengenalan}&nbsp;
                </p>
                <p>
                    <label>Alamat: </label>
                    <font style="text-transform: uppercase">${actionBean.pembida.pihak.alamat1}</font> <c:if test="${actionBean.pembida.pihak.alamat2 ne null}">,</c:if>
                    <font style="text-transform:uppercase;">${actionBean.pembida.pihak.alamat2}</font> <c:if test="${actionBean.pembida.pihak.alamat3 ne null}">,</c:if>
                    <font style="text-transform:uppercase;">${actionBean.pembida.pihak.alamat3}</font> <c:if test="${actionBean.pembida.pihak.alamat4 ne null}">,</c:if>
                    <font style="text-transform:uppercase;">${actionBean.pembida.pihak.alamat4}</font> <c:if test="${actionBean.pembida.pihak.poskod ne null}">,</c:if>
                    ${actionBean.pihak.poskod}<c:if test="${actionBean.pembida.pihak.negeri.kod ne null}">,</c:if>
                    <font style="text-transform:uppercase;">${actionBean.pembida.pihak.negeri.nama}</font> &nbsp;
                </p>
                <c:if test="${actionBean.pembida.pihak.noTelefon1 ne null}">
                    <p>
                        <label> Nombor Telefon Rumah: </label>
                        ${actionBean.pembida.pihak.noTelefon1}&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.pembida.pihak.noTelefon1 eq null}">
                    <p>
                        <label> Nombor Telefon Rumah: </label>
                        -&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.pembida.pihak.noTelefonBimbit ne null}">
                    <p>
                        <label> Nombor Telefon Bimbit: </label>
                        ${actionBean.pembida.pihak.noTelefonBimbit}&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.pembida.pihak.noTelefonBimbit eq null}">
                    <p>
                        <label> Nombor Telefon Bimbit: </label>
                        -&nbsp;
                    </p>
                </c:if>
                <br>
                <p>
                    <label> Harga Bidaan (RM): </label>
                    <s:format formatPattern="#,##0.00" value="${actionBean.pembida.lelong.hargaBidaan}" /> &nbsp;
                </p>
                <p>
                    <label>Baki (RM): </label>
                    <s:format formatPattern="#,##0.00" value="${actionBean.pembida.lelong.baki}" /> &nbsp;
                </p>
            </c:if>

            <c:if test="${actionBean.mlk eq false}">
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
                <c:if test="${actionBean.pihak.noTelefon1 ne null}">
                    <p>
                        <label> Nombor Telefon Rumah: </label>
                        ${actionBean.pihak.noTelefon1}&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.pihak.noTelefon1 eq null}">
                    <p>
                        <label> Nombor Telefon Rumah: </label>
                        -&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.pihak.noTelefonBimbit ne null}">
                    <p>
                        <label> Nombor Telefon Bimbit: </label>
                        ${actionBean.pihak.noTelefonBimbit}&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.pihak.noTelefonBimbit eq null}">
                    <p>
                        <label> Nombor Telefon Bimbit: </label>
                        -&nbsp;
                    </p>
                </c:if>
                <br>
                <p>
                    <label> Harga Bidaan (RM): </label>
                    <s:format formatPattern="#,##0.00" value="${actionBean.lelong.hargaBidaan}" /> &nbsp;
                </p>
                <p>
                    <label>Baki (RM): </label>
                    <s:format formatPattern="#,##0.00" value="${actionBean.lelong.baki}" /> &nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.atd.amaun ne null}">
                <p>
                <label>Deposit Awal (RM): </label>
                <s:format formatPattern="#,##0.00" value="${actionBean.atd.amaun}" /> &nbsp;
            </p>
            </c:if>    
            <c:if test="${actionBean.atd.amaun eq null}">
                <p>
                <label>Deposit Awal (RM): </label>
                -
            </p>
            </c:if>

                <c:if test="${actionBean.atd.caraBayaran.nama ne null}">
                <p>
                <label>Cara Bayaran : </label>
                <s:format  value="${actionBean.atd.caraBayaran.nama}" /> &nbsp;
            </p>
            </c:if>
            <c:if test="${actionBean.atd.caraBayaran.nama eq null}">
                <p>
                <label>Cara Bayaran : </label>
                -
            </p>
            </c:if>
            <c:if test="${actionBean.atd.noDokumenBayar ne null}">
                <p>
                    <label>No Rujukan : </label>
                    ${actionBean.atd.noDokumenBayar} &nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.atd.noDokumenBayar eq null}">
                <p>
                    <label>No Rujukan : </label>
                    - &nbsp;
                </p>
            </c:if>

            <c:if test="${actionBean.atd2.jenisDeposit eq 'T'}">
                <p>
                    <label>Deposit Tambahan(RM): </label>
                    <s:format formatPattern="#,##0.00" value="${actionBean.atd2.amaun}" /> &nbsp;
                </p>

                    <c:if test="${actionBean.atd.caraBayaran.nama ne null}">
                <p>
                <label>Cara Bayaran : </label>
                <s:format  value="${actionBean.atd2.caraBayaran.nama}" /> &nbsp;
            </p>
            </c:if>
            <c:if test="${actionBean.atd2.caraBayaran.nama eq null}">
                <p>
                <label>Cara Bayaran : </label>
                -
            </p>
            </c:if>
                <p>
                    <label>No Rujukan Tambahan: </label>
                    ${actionBean.atd2.noDokumenBayar} &nbsp;
                </p>
            </c:if>
                 <c:if test="${actionBean.atd2.noDokumenBayar eq null}">
                <p>
                    <label>No Rujukan : </label>
                    - &nbsp;
                </p>
            </c:if>

        </fieldset>
        &nbsp;
        <p align ="center">
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close(${actionBean.lelong.idLelong})"/>
        </p>
    </div>
</s:form>

